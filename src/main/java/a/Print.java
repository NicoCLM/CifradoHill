package a;

import org.apache.commons.math3.linear.RealMatrix;

public class Print {
	
	public static int invMod(double determinante) {
		int r = 0;
		for(int i=1; i<=28; i++) {
			if((determinante%28)*(i%28)%28 == 1) {
				r=i;
			}
		}
		return r;
	}

	public static void imprimir(RealMatrix a) {
		for (int i = 0; i < a.getRowDimension(); i++) {
			for (int j = 0; j < a.getColumnDimension(); j++) {
				System.out.print(a.getRow(i)[j]+" | ");
				if(j==a.getColumnDimension()-1) {
					System.out.println();
				}
			}
		}
		System.out.println("------------------------------------------------------");
	}
	public static void imprimir2(double[][] palabra) {
		for (int i = 0; i < palabra.length; i++) {
			for (int j = 0; j < palabra[i].length; j++) {
				System.out.print(palabra[i][j]+" | ");
				if(j==palabra[0].length-1) {
					System.out.println();
				}
			}
		}
		System.out.println("------------------------------------------------------");
	}
	public static void imprimir3(int[][] decifrada) {
		for (int i = 0; i < decifrada.length; i++) {
			for (int j = 0; j < decifrada[0].length; j++) {
				System.out.print(decifrada[i][j]+" | ");
				if(j==decifrada[0].length-1) {
					System.out.println();
				}
			}
		}
		System.out.println("------------------------------------------------------");
	}
}
