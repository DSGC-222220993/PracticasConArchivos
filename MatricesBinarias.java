import java.io.*;

public class MatricesBinarias {
    public static void main(String[] args) {
        //busca las matrices de los arcivos .mat por medio de su ruta
        double[][] mA = lecturaMatriz("C:\\Users\\Usuario\\Downloads\\a.mat");
        double[][] mB = lecturaMatriz("C:\\Users\\Usuario\\Downloads\\b.mat");
        //comprueba que las matrices sean compatibles entre si para ver si pueden ser complicadas
        if (mA != null && mB != null && mA[0].length == mB.length) {
            //calcula el producto de matrices
            double[][] producto = multiplicarMatrices(mA, mB);
            if (producto != null) {
                //guarga el producto en un nuevo archivo "c.mat"
                nuevaMatriz("C:\\Users\\Usuario\\Downloads\\c.mat", producto);
            }
        } else {
            System.out.println("las matrices no pueden ser multiplicadas");
            //esto se imprimira cuando las matrices no sean compatibles
        }
    }

    //lee la matriz
    public static double[][] lecturaMatriz(String NombreArchivo) {
        try (DataInputStream lectDatos = new DataInputStream(new FileInputStream(NombreArchivo))) {
            int filas = lectDatos.readByte();
            int columnas = lectDatos.readByte();
            //crea la matriz nueva para almacenar los datos leidos
            double[][] laMatrix = new double[filas][columnas];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    //lee y almacena los valores
                    laMatrix[i][j] = lectDatos.readDouble();
                }
            }
            return laMatrix;
            //devuelve la matriz

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static double[][] multiplicarMatrices(double[][] matrizA, double[][] matrizB) {
        int FilasA = matrizA.length; //obtiene el numero de filas en la mA
        int ColumnasA = matrizA[0].length; //obtiene el numero de columnas en la mA
        int ColumnasB = matrizB[0].length; //obtiene el numero de columnas en la mB
        //crea la matriz resultado
        double[][] resultado = new double[FilasA][ColumnasB];
        for (int i = 0; i < FilasA; i++) {
            for (int j = 0; j < ColumnasB; j++) {
                double acumulado = 0;
                //realiza la multiplicacion y el acumulado
                for (int d = 0; d < FilasA; d++) {
                    acumulado += matrizA[i][d] * matrizB[d][j];
                }
                resultado[i][j] = acumulado;
                //almacena la matriz resultante
            }
        }
        return resultado;
        //devuelve la matriz producto
    }

    public static void nuevaMatriz(String NombreArchivo, double[][] matriz) {
        //guarda la mC en un archivo
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(NombreArchivo))) {
            int dimensionH = matriz.length;
            int dimensionV = matriz[0].length;
            //establece las dimensiones
            dos.writeByte(dimensionH);
            dos.writeByte(dimensionV);
            for (int i = 0; i < dimensionH; i++) {
                for (int j = 0; j < dimensionV; j++) {
                    //escribe los valores dentro del archivo
                    dos.writeDouble(matriz[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}