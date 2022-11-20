package a;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> dic = new HashMap<String,Integer>();
		List<Integer> num = new ArrayList<Integer>();
		List<Integer> cla = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		//Diccionario
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
		try {
		int bloque = 0;
		String n1 = "";
		//Tamaño de la clave
		while(n1.isEmpty()) {
			System.out.println("Inserte el tamaño de la clave(nxn): ");
			n1 = sc.next();
			if(!n1.substring(0).matches("[0-9]*")) {
				System.out.println("  Solo puedes poner números!");
				n1 = "";
			}
			if(n1.matches("0")) {
				System.out.println("  El tamaño no puede ser 0!");
				n1 = "";
			}
			if(n1.matches("1")) {
				System.out.println("  El tamaño no puede ser 1!");
				n1 = "";
			}
		}
		bloque = Integer.parseInt(n1);
		double[][] m = new double[bloque][bloque];
		String lim = "";
		//Palabra clave
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
			if(lim.length()>=bloque*bloque) {
				System.out.println("El mensaje supero el limite porfavor aumente la matris o mande un mesaje igual a las dimenciones o menor!");
				lim = "";
				cla.clear();
			}
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
				System.out.println("el mensaje supero el limite porfavor aumente la matris o mande un mesaje igual a las dimenciones o menor");
			}
			System.out.println(Arrays.deepToString(m));

			RealMatrix ma = new Array2DRowRealMatrix(m);
			double determinante = ma.getDeterminant();
			System.out.println("Determinante: "+determinante);
			if(determinante !=0 && determinante%28 !=0 && 28%determinante !=0) {
			}else {
				System.out.println("La matris no es util porque la determinante no comple con lo requerido");
				lim = "";
				cla.clear();
			}
		}
		
		System.out.println("Su clave es: "+cla);
		
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
		    System.out.print(letra);
		}
		
		// palabra cifrada dic
		System.out.println("\nmensaje en numeros: "+num);
		//Palabra en la matriz
		double[][] palabra = new double[bloque][(num.size()/bloque)+1];
		int r = 0;
		if(cadena.length()<=((num.size()/bloque)+1)*bloque) {
			for(int i =0; i<palabra.length;i++) {
				for(int n =0; n<palabra.length;n++) {
					if(r < num.size()) {
					palabra[n][i]= num.get(r);
					r++;
					}
				}
			}
		}
		else {
			System.out.println("el mensaje supero el limite porfavor aumente la matris o mande un mesaje igual a las dimenciones o menor");
		}
		System.out.println(Arrays.deepToString(palabra));
		RealMatrix mb = new Array2DRowRealMatrix(palabra);
		RealMatrix ma = new Array2DRowRealMatrix(m);
		double determinante = ma.getDeterminant();
		System.out.println("Determinante: "+determinante);
		
		if(determinante !=0 && determinante%28 !=0 && 28%determinante !=0) {
			RealMatrix tra = ma.inverse();
			for(int i=0; i < ma.getColumnDimension();i++) {
				for(int j=0;j<ma.getRowDimension();j++) {
					//System.out.println("p ["+i+"] ["+ j +"]: "+tra.getData()[i][j]);
				}
			}
			RealMatrix multiplicacion = ma.multiply(mb);
			System.out.println("la multiplicacion de las matrices: "+multiplicacion);
			double numero;
			double[][] Encriptado = new double[bloque][((num.size()/bloque)+1)];
			for(int i =0;i<bloque;i++) {
				for(int j =0;j<((num.size()/bloque)+1);j++) {
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
		
	}catch(NoSuchElementException e) {
			
		}

	}


}