package a;


import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> dic = new HashMap<String,Integer>();
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
		
		double[][] m = {{7,8,9},{7,87,79},{78,89,90}};
		RealMatrix ma = new Array2DRowRealMatrix(m);
		System.out.println("Determinante: "+ma.getDeterminant());
		RealMatrix tra = ma.inverse();
		
		
		
		for(int i=0; i < ma.getColumnDimension();i++) {
			for(int j=0;j<ma.getRowDimension();j++) {
				System.out.println("p ["+i+"] ["+ j +"]: "+tra.getData()[i][j]);
			}
		}
		
	}

}