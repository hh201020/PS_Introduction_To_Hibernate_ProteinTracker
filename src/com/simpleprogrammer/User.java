package com.simpleprogrammer;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id;
	private String name;
	
	private ProteinData proteinData;
	
	private List<UserHistory> history = new ArrayList<>();

	public User(){
		setProteinData(new ProteinData());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProteinData getProteinData() {
		return proteinData;
	}
	public void setProteinData(ProteinData proteinData) {
		this.proteinData = proteinData;
		proteinData.setUser(this);
	}
	public List<UserHistory> getHistory() {
		return history;
	}
	public void setHistory(List<UserHistory> history) {
		this.history = history;
	}
	
	public void addHistory(UserHistory historyItem){
		historyItem.setUser(this);
		history.add(historyItem);
	}
}
