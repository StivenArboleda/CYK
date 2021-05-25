package model;

import java.util.HashMap;

public class Grammar {

	/**
	 * gramm es una Hash Map que contiene todas las variables de la gramática
	 */
	private HashMap<String, Variable> gramm;

	/**
	 * initialSymbolG es la variable inicial de la gramática
	 */
	private String initialSymbolG;

	/**
	 * Crea una nueva gramática con su Hash Map de variables vacío
	 */
	public Grammar() {
		this.gramm = new HashMap<String, Variable>();
	}

	/**
	 *
	 * @return retorna la gramatica
	 */
	
	public HashMap<String, Variable> getGramm() {
		return gramm;
	}

	/**
	 *
	 * @return retorna el simbolo de la gramatica inicial
	 */
	public String getInitialSymbolG() {
		return initialSymbolG;
	}

	/**
	 *
	 * @param s ingresa un arreglo de strings por parametro
	 * @param v Ingresa una una v de tipo variable por parametro
	 */
	private void setProductions(String[] s, Variable v) {
		for (int i = 1; i < s.length ; i++) {
			v.addProductions(s[i]);
		}
	}


	/**
	 * Este método permite agregar las variables a la gramática leyendo línea por
	 * Linea que usuario ingresa
	 * @param variables Ingresa una variable de tipo String por parametro
	 */
	public void fillGrammar(String variables) {
		
		String[] gic = variables.split("\n");
		
		for (String string : gic) {
			String[] s = string.split(" ");
			String symbolInitial = s[0];
			
			Variable current = gramm.get(s[0]);
			
			current = new Variable(symbolInitial);
			
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

