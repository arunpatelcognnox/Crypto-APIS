package com.arun.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
	
	@Id
	private String id;
	
	private String apikey;
	private String walletId;
	private String blockchain;
	
	private String network;
	
	private String address;
	private String amount;
	private String feePriority;
	private String prepareStrategy;
	
	private String callbackUrl;
	private String callbackSecretKey;
	private String note;
	private String context;
	
	private Status status;
	private Date createdDate;
	
	
		public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public String getBlockchain() {
		return blockchain;
	}
	public void setBlockchain(String blockchain) {
		this.blockchain = blockchain;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFeePriority() {
		return feePriority;
	}
	public void setFeePriority(String feePriority) {
		this.feePriority = feePriority;
	}
	public String getPrepareStrategy() {
		return prepareStrategy;
	}
	public void setPrepareStrategy(String prepareStrategy) {
		this.prepareStrategy = prepareStrategy;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getCallbackSecretKey() {
		return callbackSecretKey;
	}
	public void setCallbackSecretKey(String callbackSecretKey) {
		this.callbackSecretKey = callbackSecretKey;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", apikey=" + apikey + ", walletId=" + walletId + ", blockchain=" + blockchain
				+ ", network=" + network + ", address=" + address + ", amount=" + amount + ", feePriority="
				+ feePriority + ", prepareStrategy=" + prepareStrategy + ", callbackUrl=" + callbackUrl
				+ ", callbackSecretKey=" + callbackSecretKey + ", note=" + note + ", context=" + context + ", status="
				+ status + ", createdDate=" + createdDate + "]";
	}
}
