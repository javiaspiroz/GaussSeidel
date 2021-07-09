package main;

public class Gauss_Seidel {
	public static final int MAX_ITERATIONS = 100;//número máximo de iteraciones
	private double[][] M;//matriz M introducida por el usuario

	public Gauss_Seidel(double[][] matrix) {//constructor de la clase
		M = matrix;
	}

	public void print() {//método que imprime la matriz
		int n = M.length;
		for (int i = 0; i < n; i++) {
			System.out.print("(");
			for (int j = 0; j < n + 1; j++) {
				if (j == n) {
					System.out.print(M[i][j] + ")\n");
				} else {
					System.out.print(M[i][j] + " ");
				}
			}
		}
	}

	public void solve() {
		int iterations = 0;//numero actual de iteraciones
		int n = M.length;//dimensión de la matriz
		boolean stop = true;//boolean que controla el bucle
		double precision = 0.001;//precisión que se desea obtener
		double error = Double.MAX_VALUE;//error inicial establecido al máximo
		double[] previousVariableValues = new double[n];//array que almacena las x_i de la anterior iteración
		
		//impresión de x_0
		System.out.print("\nX_0 = {");
		for (int i=0; i<n;i++){
			System.out.print("0.0 ");
		}
		System.out.print("}\n\n");
		
		
		while (stop) {//loop que se repite hasta obtener la precisión deseada o llegar al número máximo de iteraciones
			iterations++;
			double[] currentVariableValues = new double[n];//array que almacena las x_i de la iteración actual

			for (int i = 0; i < n; i++) {//loop que recorre la matriz y calcula los x_i correspondientes
				currentVariableValues[i] = M[i][n];//reestablecemos el valor para calcular una nueva fila
				for (int j = 0; j < n; j++) {
					if (i > j) {
						currentVariableValues[i] -= M[i][j] * currentVariableValues[j];
					}
					if (i < j) {
						currentVariableValues[i] -= M[i][j] * previousVariableValues[j];
					}
				}
				currentVariableValues[i] /= M[i][i];
			}
			//imprimimos los valores obtenidos en esta iteración
			System.out.print("X_" + iterations + " = {");
			for (int j = 0; j < currentVariableValues.length; j++)
				System.out.print(currentVariableValues[j] + " ");
			System.out.println("}");
			
			//calculamos el error
			error = 0;			
			for (int i=0; i<currentVariableValues.length; i++){
				error += Math.pow(currentVariableValues[i]-previousVariableValues[i], 2);
			}
			error=Math.sqrt(error);
			System.out.println("Error: " + error + "\n");
			
			//comprobamos si tenemos que calcular otra nueva iteración
			if (error < precision || iterations >= MAX_ITERATIONS) {
				stop = false;
			}

			previousVariableValues = currentVariableValues;//igualamos para calcular la nueva iteración
		}
	}
}