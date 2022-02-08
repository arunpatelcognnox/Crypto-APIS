package com.arun;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class FirstController {

	private RestTemplate restTemplate;
	
	public FirstController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping
	public String welcomePage(Model model) {
		model.addAttribute("message", "");
		return "first";
	}
	
	@PostMapping
	public String save( @RequestParam("apikey") String apikey, @RequestParam("walletId") String walletId, @RequestParam("blockchain") String blockchain, @RequestParam("network") String network,
			@RequestParam("address") String address, @RequestParam("amount") String amount, @RequestParam("feePriority") String feePriority, @RequestParam("prepareStrategy") String prepareStrategy,
			@RequestParam("callbackUrl") String callbackUrl, @RequestParam("callbackSecretKey") String callbackSecretKey, @RequestParam("note") String note, @RequestParam("context") String context, Model model) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		httpHeaders.add("X-API-Key", apikey);
		
		Map<String, Object> recipients = new LinkedHashMap<>();
		recipients.put("address", address);
		recipients.put("amount", amount);
		
		Map<String, Object> item = new LinkedHashMap<>();
		if(callbackUrl !=null && !callbackSecretKey.isEmpty()) {
			item.put("callbackSecretKey", callbackSecretKey);
			item.put("callbackUrl", callbackUrl);
		}
		item.put("feePriority", feePriority);
		item.put("note", note);
		item.put("prepareStrategy", prepareStrategy);
		item.put("recipients", List.of(recipients));
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("item", item);
		
		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put("context", context);
		payload.put("data", data);
		
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, httpHeaders);
		
		String url = "https://rest.cryptoapis.io/v2/wallet-as-a-service/wallets/"+walletId+"/"+blockchain+"/"+network+"/transaction-requests";
		String returnData = "";
		try {
			Map<String, Object> response = restTemplate.postForEntity(url, requestEntity, Map.class).getBody();
			System.out.println("output => "+response);
			returnData = "success";
		}catch (Exception e) {
			System.out.println(e);
			returnData = "error";
		}
		model.addAttribute("message", returnData);
		return "first";
	}
}
