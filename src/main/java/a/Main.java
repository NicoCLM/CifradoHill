package a;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> dic = new HashMap<String,Integer>();
		List<Integer> num = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		dic.put("A",0);
		dic.put("B",1);
		dic.put("C",2);
		dic.put("D",3);
		dic.put("E",4);
		dic.put("F",5);
		dic.put("G",6);
		dic.put("H",7);
		dic.put("I",8);
		dic.put("J",9);
		dic.put("K",10);
		dic.put("L",11);
		dic.put("M",12);
		dic.put("N",13);
		dic.put("Ñ",14);
		dic.put("O",15);
		dic.put("P",16);
		dic.put("Q",17);
		dic.put("R",18);
		dic.put("S",19);
		dic.put("T",20);
		dic.put("U",21);
		dic.put("V",22);
		dic.put("W",23);
		dic.put("X",24);
		dic.put("Y",25);
		dic.put("Z",26);
		dic.put(" ",27);
		int bloque = 3;
		String cadena = "hey menes";
		for (int i = 0; i < cadena.length(); i++){
		    char letra = cadena.charAt(i);
		    letra = Character.toUpperCase(letra);
		    String a = String.valueOf(letra);
		    Integer x = dic.get(a);
		    num.add(x);
		    System.out.println(letra);
		}
		System.out.println("mensaje en numeros: "+num);

		double[][] palabra = new double[bloque][bloque];
		int r = 0;
		if(cadena.length()<=bloque*bloque) {
			for(int i =0; i<palabra.length;i++) {
				for(int n =0; n<palabra.length;n++) {
					if(r < num.size()) {
					palabra[i][n]= num.get(r);
					r++;
					}
				}
			}
		}
		else {
			System.out.println("el mensaje supero el limite porfavor aumente la matris o mande un mesaje igual a las dimenciones o menor");
		}
		RealMatrix mb = new Array2DRowRealMatrix(palabra);
		double[][] m = {{1,0,1},{1,2,1},{1,1,4}};
		RealMatrix ma = new Array2DRowRealMatrix(m);
		double determinante = ma.getDeterminant();
		System.out.println("Determinante: "+determinante);
		if(determinante !=0 && determinante%28 !=0 && 28%determinante !=0) {
			RealMatrix tra = ma.inverse();
			for(int i=0; i < ma.getColumnDimension();i++) {
				for(int j=0;j<ma.getRowDimension();j++) {
					System.out.println("p ["+i+"] ["+ j +"]: "+tra.getData()[i][j]);
				}
			}
			RealMatrix multiplicacion = mb.multiply(ma);
			System.out.println("la multiplicacion de las matrices "+multiplicacion);
			double numero;
			double[][] Encriptado = new double[bloque][bloque];
			for(int i =0;i<bloque;i++) {
				for(int j =0;j<bloque;j++) {
					numero=multiplicacion.getEntry(i, j);
					numero = numero%28;
					Encriptado[i][j] =numero;
				}
			}
			RealMatrix MatrixCifrada = new Array2DRowRealMatrix(Encriptado);
			System.out.println("la matrix encriptada es "+MatrixCifrada);
		}
		else {
			System.out.println("la matris no es util porque la determinante no comple con lo requerido");
		}

	}

}