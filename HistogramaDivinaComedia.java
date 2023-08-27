import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistogramaDivinaComedia {
    public static void main(String[] args) {

        String file = "C:\\Users\\Usuario\\Documents\\divina_comedia_sp.txt";//ruta al archivo
        List<Integer> histograma = new ArrayList<>(10);
        //se crea la lista "histograma" para almacenar con que frecuencia se repite la longitud comun de las distinta palabras

        for (int i = 0; i <= 10; i++) {//base cero
            histograma.add(0);
        }

        StringBuilder palabraAlmacen = new StringBuilder();
        // con el objeto palabraAlmacen de tipo StringBuilder se construye y almacenan las palbras que van leyendo

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            //con el bloque try se lee el archivo linea por linea
            int car;
            //almacena el valor numerico de cada caracter leido
            while ((car = br.read()) != -1) {
            //este bucle devuelve un valor distinto a -1, que indica el final del archivo
                char caracter = (char) car;
                //el entero car se concierte en char
                if (Character.isLetter(caracter)) {
                //verifica que los caracteres sean letras
                    palabraAlmacen.append(caracter);
                } else if (palabraAlmacen.length() > 0) {
                //aqui llegan los caracteres que no son letras
                    int largo=calcularLong(palabraAlmacen.toString());
                    if ( largo>=2 && largo<=10){
                    //verifica la palabra este dentro de un rango de 2 o 10 caracteres
                        histograma.set( largo,histograma.get(largo)+1);
                        //actualizacion de conteo segun el rango
                    }
                    palabraAlmacen.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 2; i <= 10; i++) { //imprime el histograma
            System.out.println("Se contaron "+ histograma.get(i)+ " palabras con " +i+ " caracteres.");
        }
    }

    private static int calcularLong(String palabra) {
        // el metodo toma la cadena palabra de entrada y calcula su longitud dependiendo del numero de letras
        int largo=0;
        for (char car : palabra.toCharArray()) {
        //el for recorre cada caracter de "palabra" y el metodo convierte la cadena en un arreglo
            if (Character.isLetter(car)) {
                largo ++;
            }
        }
    return largo;
    //termina el bucle y devuelve el valor de la variable largo
    }
}