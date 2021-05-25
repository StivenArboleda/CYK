package model;

import java.util.HashSet;

public class Variable {

	private HashSet<String> gra;
	private String key;

	/**
	 * Metodo constructor
	 * @param key
	 */
	public Variable(String key) {
		this.gra = new HashSet<>();
		this.key = key;
	}

	/**
	 *
	 * @return gra : Retorna la gramatica
	 */

	public HashSet<String> getGra() {
		return gra;
	}

	/**
	 * @param gra Ingresa gra por parametro
	 */

	public void setGra(HashSet<String> gra) {
		this.gra = gra;
	}

	/**
	 * @return key : Retorna la key
	 */

	public String getKey() {
		return key;
	}

	/**
	 *
	 * @param key Ingresa la key por parametro
	 */

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 *
	 * @param s Ingresa s de tipo String por parametro
	 */

	public void addProductions(String s) {
		if(!gra.contains(s)) {
			gra.add(s);
		}
	}
	

}
