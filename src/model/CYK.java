package model;

import java.util.ArrayList;
import java.util.Iterator;

public class CYK {


	private Grammar g;

	/**
	 *
	 * @param g Ingresa g de tipo Grammar por parametro
	 */
	public CYK(Grammar g) {
		this.g = g;
	}

	/**
	 * Este es el metodo que verifica si cadena es generada por G
	 * @param chain : Ingresa la cadena por parametro
	 * @return containsInitialSymbol : Retorna si la cadena es aceptada o no
	 */
	public boolean isCYK(String chain) {

		char[] chainChar = chain.toCharArray();
		String[][][] cykMatrix = new String[chainChar.length][chainChar.length][]; 
		iteratorCYKMatrix(chainChar, cykMatrix);
		
		for (int j = 1; j < cykMatrix.length; j++) {
			for (int i = 0; i < cykMatrix.length - j; i++) {
				ArrayList<String> productionsFuture = new ArrayList<>();
				for (int k = 0; k < j; k++) {
					
					String[] v = cykMatrix[i][k];   //el subindice (IK) para hallar Xij
					String[] v2 = cykMatrix[i + k + 1][j - k - 1]; //el subindice (i+k, j-k) para hallar Xij
					
					if(v != null && v2 !=null) { //no tomar valores nulls de la matriz escalera
						
						for (int l = 0; l < v.length; l++) {
							for (int m = 0; m < v2.length; m++) {
								productionsFuture.add(v[l] + v2[m]);
							}
						}
					}
				}
				
				CheckProduction(productionsFuture, cykMatrix, i, j);
			}
		}
		
		boolean containsInitialSymbol = false;
		
		String[] variableContains = cykMatrix[0][cykMatrix.length -1];
		
		if(variableContains != null) {
			
			for (int i = 0; i < variableContains.length && !containsInitialSymbol; i++) {
				if(variableContains[i].equalsIgnoreCase(g.getInitialSymbolG())) {
					containsInitialSymbol = true;
				}
			}
		}
		return containsInitialSymbol;
	}


	/**
	 *Este método verifica si las variables de la gramática producen al menos una producción única
	 * @param productionsFuture es el HashSet que almacena los posibles producciones de la gramática
	 * @param cykMatrix es una matriz de cadenas que corresponde al cyk matriz.
	 * @param i es un entero que corresponde a la posición de la cola de la matriz.
	 * @param j es un entero que corresponde a la posición de la columna de la matriz.
	 */
	public void CheckProduction(ArrayList<String> productionsFuture, String[][][] cykMatrix, int i, int j) {
		
		if(productionsFuture.size() > 0) {
			ArrayList<String> variablesTotals = new ArrayList<>();
			
			for (Variable v : g.getGramm().values()) {
				
				boolean found = false;
				
				for (Iterator<String> v2 = productionsFuture.iterator(); v2.hasNext() && !found; ) {
					String s = (String) v2.next();
					if(v.getGra().contains(s)) {
						found = true;
						variablesTotals.add(v.getKey());
					}
				}
			}
			if(variablesTotals.size() > 0) {
				String[] out = new String[variablesTotals.size()];
				for (int k = 0; k < out.length; k++) {
					out[k] =  variablesTotals.get(k);
				}
				cykMatrix[i][j] = out;
			}else {
				cykMatrix[i][j] = null;
			}
		}else {
			cykMatrix[i][j] = null;
		}
		
	}

	/**
	 * Este método inicializa la matriz de la primera columna
	 * @param chainChars es una matriz de char que corresponde a los caracteres de la cadena de prueba.
	 * @param cykMatrix es una matriz de cadenas que corresponde a la matriz cyk.
	 */
	
	private void iteratorCYKMatrix(char[] chainChars, String[][][] cykMatrix) {
		
		for(int j = 0; j < chainChars.length; j++) {
			ArrayList<String> current = new ArrayList<>();
			
			for (Variable v : g.getGramm().values()) {
				if (v.getGra().contains(chainChars[j] + "")) {
					current.add(v.getKey());
				}	
			}
			
			if(current.size() >= 1) {
				
				String[] out = new String[current.size()];
				out[0] = current.get(0);
				cykMatrix[j][0] = out;
 			
			}else {
 				cykMatrix[j][0] = null;
 			}
		}

		
	}
	
}
