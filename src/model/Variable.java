package model;

import java.util.HashSet;

public class Variable {
	
	private HashSet<String> gra;
	private String key;
	
	public Variable(String key) {
		this.gra = new HashSet<>();
		this.key = key;
	}

	public HashSet<String> getGra() {
		return gra;
	}

	public void setGra(HashSet<String> gra) {
		this.gra = gra;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void addProductions(String s) {
		if(!gra.contains(s)) {
			gra.add(s);
		}
	}
	

}
