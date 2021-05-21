package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class CYK {
	
	public CYK() {
		
	}
	
	private Grammar g;
	
	public boolean isCYK(String chain) {
		
		boolean generator = false;
		
		char[] chainChar = chain.toCharArray();
		String[][][] cykMatrix = new String[chainChar.length][chainChar.length][0]; 
		iteratorCYKMatrix(chainChar, cykMatrix);
		
		for (int j = 1; j < cykMatrix.length; j++) {
			for (int i = 0; i < cykMatrix.length - 1; i++) {
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
				//
			}
		}
		
		return generator;
	}
	
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
