package a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;


public class Main {
	/*
	 * # Piza, Hernandez, Villarreal, Camacho 
	 * # Encriptado y Desencriptado de Hill
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Print pri = new Print();
		HashMap<String,Integer> dic = new HashMap<String,Integer>();
		HashMap<Integer,String> dic2 = new HashMap<Integer,String>();
		List<Integer> num = new ArrayList<Integer>();
		List<Integer> cla = new ArrayList<Integer>();
		List<Integer> inver = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		//Diccionario
		dic.put("A",0);		dic2.put(0,"A");
		dic.put("B",1);		dic2.put(1,"B");
		dic.put("C",2);		dic2.put(2,"C");
		dic.put("D",3);		dic2.put(3,"D");
		dic.put("E",4);		dic2.put(4,"E");
		dic.put("F",5);		dic2.put(5,"F");
		dic.put("G",6);		dic2.put(6,"G");
		dic.put("H",7);		dic2.put(7,"H");
		dic.put("I",8);		dic2.put(8,"I");
		dic.put("J",9);		dic2.put(9,"J");
		dic.put("K",10);	dic2.put(10,"K");
		dic.put("L",11);	dic2.put(11,"L");
		dic.put("M",12);	dic2.put(12,"M");
		dic.put("N",13);	dic2.put(13,"N");
		dic.put("Ñ",14);	dic2.put(14,"Ñ");
		dic.put("O",15);	dic2.put(15,"O");
		dic.put("P",16);	dic2.put(16,"P");
		dic.put("Q",17);	dic2.put(17,"Q");
		dic.put("R",18);	dic2.put(18,"R");
		dic.put("S",19);	dic2.put(19,"S");
		dic.put("T",20);	dic2.put(20,"T");
		dic.put("U",21);	dic2.put(21,"U");
		dic.put("V",22);	dic2.put(22,"V");
		dic.put("W",23);	dic2.put(23,"W");
		dic.put("X",24);	dic2.put(24,"X");
		dic.put("Y",25);	dic2.put(25,"Y");
		dic.put("Z",26);	dic2.put(26,"Z");
		dic.put(" ",27);	dic2.put(27," ");
		try {
		int bloque = 0;
		//Tamaño de la clave
		String lim = "";
		//Palabra clave
		double[][] m = null;
		while(lim.isEmpty()) {
			System.out.println("Inserte la palabra calve: ");
			lim = sc.nextLine();
			//Transforma palabra a números
			for (int i = 0; i < lim.length(); i++){
			    char letra1 = lim.charAt(i);
			    letra1 = Character.toUpperCase(letra1);
			    String a = String.valueOf(letra1);
			    Integer x = dic.get(a);
			    cla.add(x);
			}
			//Es una letra
			for (int i = 0; i < lim.length(); i++){
			    char letra = lim.charAt(i);
			    letra = Character.toUpperCase(letra);
				if(dic.get(String.valueOf(letra)) == null) {
					System.out.println("  Solo puedes poner letras!");
					lim = "";
					cla.clear();
				}
			}
			if(lim.length() > 9 || lim.length() < 4) {
				System.out.println("  El mensaje supero el limite!");
				lim = "";
				cla.clear();
			}
			if(lim.length() == 4) {
				bloque = lim.length()/2;
			}
			if(lim.length() == 9) {
				bloque = lim.length()/3;
			}
			m = new double[bloque][bloque];
			//Clave a matriz
			int r2 = 0;
			if(lim.length()<=bloque*bloque) {
				for(int i =0; i<m.length;i++) {
					for(int n =0; n<m.length;n++) {
						if(r2 < cla.size()) {
						m[n][i]= cla.get(r2);
						r2++;
						}
					}
				}
			}

			else {
				System.out.println("el mensaje supero el limite porfavor aumente la matriz o mande un mesaje igual a las dimenciones o menor");
			}
			double determinante = 0;
			if(!lim.isEmpty()) {
				RealMatrix ma = new Array2DRowRealMatrix(m);
				determinante = (new LUDecomposition(ma).getDeterminant());
				System.out.println("Determinante: "+determinante);
				if(determinante !=0 && determinante%28 !=0 && 28%determinante !=0 && determinante%2 !=0) {
				}else {
					System.out.println("  La matriz no es util porque la determinante no comple con lo requerido");
					System.out.println("------------------------------------------------------");
					lim = "";
					cla.clear();
				}
			}


			
			
			if(pri.invMod(determinante) == 0) {
				System.out.println();
				System.out.println(pri.invMod(determinante));
				System.out.println("  La matriz no es util porque la determinante no comple con lo requerido");
				System.out.println("------------------------------------------------------");
				lim = "";
				cla.clear();
			}
		}

		
		System.out.println("Su clave es: "+cla);
		System.out.println("------------------------------------------------------");
		String cadena = "";
		//Palabra a cifrar
		while(cadena.isEmpty()) {
			System.out.println("Inserte la palabra a cifrar");
			cadena = sc.nextLine();
			for (int i = 0; i < cadena.length(); i++){
			    char letra = cadena.charAt(i);
			    letra = Character.toUpperCase(letra);
				if(dic.get(String.valueOf(letra)) == null) {
					System.out.println("  Solo puedes poner letras!");
					cadena = "";
				}
			}
		}
		//divide la palabra por letras
		for (int i = 0; i < cadena.length(); i++){
		    char letra = cadena.charAt(i);
		    letra = Character.toUpperCase(letra);
		    String a = String.valueOf(letra);
		    Integer x = dic.get(a);
		    num.add(x);
		}
		
		// palabra cifrada dic
		System.out.println("\nMensaje en numeros: "+num);
		//Palabra cifrada en la matriz
		int tama = (num.size()/bloque)+1;
		if(tama < 3) {
			tama = 3;
		}else {
			tama = (num.size()/bloque)+1;
		}
		double[][] palabra = new double[bloque][tama];
		int r = 0;
		for (double[] row : palabra) {
            Arrays.fill(row, 27);
		}
		if(cadena.length()<=(tama)*bloque) {
			for(int i =0; i<(tama);i++) {
				for(int n =0; n<palabra.length;n++) {
					if(r < num.size()) {
					palabra[n][i]= num.get(r);
					r++;
					}
				}
			}
		}
		else {
			System.out.println("el mensaje supero el limite porfavor aumente la matriz o mande un mesaje igual a las dimenciones o menor");
		}

		pri.imprimir2(palabra);
		RealMatrix mb = new Array2DRowRealMatrix(palabra);
		RealMatrix ma = new Array2DRowRealMatrix(m);
		double determinante = (new LUDecomposition(ma).getDeterminant());
		//Revisar si la clave nos funciona o no
		if(determinante !=0 && determinante%28 !=0 && 28%determinante !=0) {
			RealMatrix tra = (new LUDecomposition(ma).getSolver().getInverse());
			for(int i=0; i < ma.getColumnDimension();i++) {
				for(int j=0;j<ma.getRowDimension();j++) {
					//System.out.println("p ["+i+"] ["+ j +"]: "+tra.getData()[i][j]);
				}
			}
			//realiza multiplicación de matrices
			RealMatrix multiplicacion = ma.multiply(mb);
			System.out.println("la multiplicacion de las matrices: ");
			pri.imprimir(multiplicacion);
			double numero;
			//Modulo 28 a la multiplicación de matrices
			double[][] Encriptado = new double[bloque][((num.size()/bloque)+1)];
			for(int i =0;i<bloque;i++) {
				for(int j =0;j<((num.size()/bloque)+1);j++) {
					numero=multiplicacion.getEntry(i, j);
					numero = numero%28;
					Encriptado[i][j] =numero;
				}
			}
			//Inversa de la matriz Clave
			RealMatrix inve = (new LUDecomposition(ma).getSolver().getInverse());
			System.out.println("Inversa");
			pri.imprimir(inve);
			//Multiplico la inversa de la matriz clave por el mensaje cifrado
			RealMatrix des = inve.multiply(multiplicacion);
			//Imprimo matriz cifrada
			RealMatrix matrixCifrada = new Array2DRowRealMatrix(Encriptado);
			System.out.println("la matriz encriptada es ");
			pri.imprimir(matrixCifrada);
			
			System.out.println("Mensaje Encriptado: ");
			for(int i=0; i < matrixCifrada.getColumnDimension();i++) {
				for(int j=0;j<matrixCifrada.getRowDimension();j++) {
					System.out.print(dic2.get((int)matrixCifrada.getColumn(i)[j]));
				}
			}
			System.out.println("\n"+"------------------------------------------------------");
			//Decifrado
			
			//cofactor + Inversa Modular + Mod28
			RealMatrix cof = inve.scalarMultiply(determinante);
			int dete = pri.invMod(determinante);
			double[][] co = new double[bloque][bloque];
			for(int i=0; i<co.length;i++) {
				for(int j=0; j<co.length; j++) {
					//co[j][i] = (int)(cof.getEntry(j, i)*dete);
					if(cof.getEntry(j, i) <0) {
						co[j][i] = (int)((cof.getEntry(j, i)*dete)-0.9);
					}
					if(cof.getEntry(j, i) >= 0) {
						co[j][i] = (int)((cof.getEntry(j, i)*dete)+0.9);
					}
				}
			}
			System.out.println("Cofactor");
			pri.imprimir2(co);
			for(int i=0; i<co.length;i++) {
				for(int j=0; j<co.length; j++) {
					co[j][i] = Math.floorMod((int) co[j][i], 28);
				}
			}
			System.out.println("Mod 28");
			pri.imprimir2(co);
			RealMatrix cofactor = new Array2DRowRealMatrix(co);
			RealMatrix multiCof = cofactor.multiply(matrixCifrada);
			int[][] desc = new int[bloque][((num.size()/bloque)+1)];
			for(int i=0; i<((num.size()/bloque)+1);i++) {
				for(int j=0; j<desc.length; j++) {
					desc[j][i] =(int)(multiCof.getEntry(j, i));
				}
			}
			System.out.println("Mensaje cofactor");
			pri.imprimir3(desc);
			for(int i=0; i<((num.size()/bloque)+1);i++) {
				for(int j=0; j<desc.length; j++) {
					desc[j][i] = desc[j][i]%28;
					inver.add(desc[j][i]%28);
				}
			}
			System.out.println("Mensaje Descifrado: ");
			pri.imprimir3(desc);
			//Imprimo matriz luego de la multiplicación de matriz cifrada con la inversa de la clave
			System.out.println("Mensaje Desencriptado: ");
			for(int i=0;i<inver.size();i++) {
				System.out.print(dic2.get(inver.get(i)));
			}
			
		}
		else {
			System.out.println("la matriz no es util porque la determinante no comple con lo requerido");
		}
		sc.close();
		
	}catch(NoSuchElementException e) {
			
		}

	}
}