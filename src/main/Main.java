package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
        int n;
        double[][] M;
        //pedimos la matriz aumentada
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);
 
        System.out.println("Enter the number of variables in the equation:");
        n = Integer.parseInt(reader.readLine());
        M = new double[n][n+1];
        System.out.println("Enter the augmented matrix:");
 
        for (int i = 0; i < n; i++){
            StringTokenizer strtk = new StringTokenizer(reader.readLine());
 
            while (strtk.hasMoreTokens())
                for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++)
                    M[i][j] = Integer.parseInt(strtk.nextToken());
        }
        //creamos una clase Gauss_Seidel donde realizaremos los cálculos 
        Gauss_Seidel gausSeidel = new Gauss_Seidel(M);

        writer.println();
        gausSeidel.print();
        gausSeidel.solve();
    }
}