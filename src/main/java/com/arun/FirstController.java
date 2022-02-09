package com.arun;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.arun.entity.Order;
import com.arun.entity.OrderRepository;
import com.arun.entity.Status;

@Controller
public class FirstController {

	private RestTemplate restTemplate;
	private OrderRepository orderRepository;
	private String message = "";

	public FirstController(RestTemplate restTemplate, OrderRepository orderRepository) {
		this.restTemplate = restTemplate;
		this.orderRepository = orderRepository;
	}

	@GetMapping
	public String welcomePage(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("message", message);
		return "first";
	}
	
	@PostMapping
	public String save(@ModelAttribute Order order) {
			
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		httpHeaders.add("X-API-Key", order.getApikey());
		
		Map<String, Object> recipients = new LinkedHashMap<>();
		recipients.put("address", order.getAddress());
		recipients.put("amount", order.getAmount());
		
		Map<String, Object> item = new LinkedHashMap<>();
		if(order.getCallbackUrl() !=null && !order.getCallbackUrl().isEmpty()) {
			item.put("callbackSecretKey", order.getCallbackSecretKey());
			item.put("callbackUrl", order.getCallbackUrl());
		}
		item.put("feePriority", order.getFeePriority());
		item.put("note", order.getNote());
		item.put("prepareStrategy", order.getPrepareStrategy());
		item.put("recipients", List.of(recipients));
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("item", item);
		
		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put("context", order.getContext());
		payload.put("data", data);
		
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, httpHeaders);
		
		String url = "https://rest.cryptoapis.io/v2/wallet-as-a-service/wallets/"+order.getWalletId()+"/"+order.getBlockchain()+"/"+order.getNetwork()+"/transaction-requests";
		try {
			Map<String, Object> response = restTemplate.postForEntity(url, requestEntity, Map.class).getBody();
			System.out.println("output => "+response);
			order.setStatus(Status.SUCCESS);
		}catch (Exception e) {
			System.out.println(e);
			order.setStatus(Status.ERROR);
		}
		
		order.setId(UUID.randomUUID().toString());
		order.setCreatedDate(new Date());
		orderRepository.save(order);
		
		message = ""+order.getStatus();
		return "redirect:/";
	}
}
