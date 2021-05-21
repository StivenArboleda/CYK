package model;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Grammar {
	
	private String w;
	private String initialSymbolG;
	
	public HashMap<String, Variable> getGramm() {
		return gramm;
	}

	public void setGramm(HashMap<String, Variable> gramm) {
		this.gramm = gramm;
	}

	private HashMap<String, Variable> gramm; 
	
	
	public Grammar() {
		
		this.gramm = new HashMap<String, Variable>();
	}

	private void setProductions(String[] s, Variable v) {
		for (int i = 1; i < s.length; i++) {
			v.addProductions(s[i]);
		}
	}
	
	public void fillGrammar(String variables) {
		
		String[] gic = variables.split("\n");
		
		for (String string : gic) {
			String[] s = string.split(" ");
			String symbolInitial = s[0];

			Variable current = gramm.get(s[0]);

			if (gramm.size() == 0) {
				gramm.put(symbolInitial, current);
				initialSymbolG = symbolInitial;
			}else {
				gramm.put(symbolInitial, current);
			}
			setProductions(s, current);			
		}
	}
	
	
}

