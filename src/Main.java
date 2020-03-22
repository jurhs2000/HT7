/**
 * 
 * Universidad del Valle de Guatemala
 * Algoritmos y estructuras de datos
 * Hoja de trabajo 7
 * 17/03/2020
 * 
 * @author Julio Herrera 19402
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	/**
	 * 
	 * Este método es utilizado para leer el archivo datos.txt. La lectura se
	 * realiza para todas las líneas del archivo y separa cada caracter que tenga el
	 * archivo para agregarlo a la lista de Strings que devolverá.
	 * 
	 * @return una lista de todos los elementos del archivo separados
	 * @throws Exception excepción general para la lectura del archivo
	 */
	public static ArrayList<String> textReader(String fileName) throws Exception {
		final String bar = File.separator;
		final String dir = System.getProperty("user.dir");
		/**
		 * AQUI SE LEE EL ARCHIVO TXT si no corre se debe de reemplazar en el parentesis
		 * (dir + barra +"NOMBRE DEL FOLDER EN DONDE ESTA EL PROYECTO" +barra+
		 * "datos.txt") El error del archivo de texto puede pasar si se corre el
		 * programa en eclipse y no en consola o tambien sucede al trabajar con paquetes
		 */
		final File file = new File(dir + bar + fileName);
		if (!file.exists()) {
			throw new FileNotFoundException("No se encontro el archivo, ver lineas comentadas");
		}
		FileReader fr;
		fr = new FileReader(file);
		final BufferedReader br = new BufferedReader(fr);
		ArrayList<String> lineList = new ArrayList<>(); //ya que solo se agrega al final
		String line = "";
		while ((line = br.readLine()) != null) {
			lineList.add(line);
		}
		br.close();
		return lineList;
	}

	public static void main(final String[] args) throws Exception {
		/**
		 * Menu de eleccion de archivo a leer
		 */
		Scanner scan = new Scanner(System.in);
		boolean isCorrect = false;
		String fileName = "";
		while (!isCorrect) {
            System.out.println("Escriba el nombre del archivo que va a leer");
            System.out.println("Si presiona solo enter se escoge por default ('text.txt'): ");
			fileName = scan.nextLine();
			if (fileName.split(".").length < 1) {
                isCorrect = false;
                System.out.println("Indique la extension del archivo .txt");
            } else {
                isCorrect = true;
            }
		}
		if (fileName.equals("")) {
			fileName = "text.txt";
		}
		/**
		 * Se lee el archivo de texto
		 */
        ArrayList<String> listDictionary = textReader("dictionary.txt");
        ArrayList<String> sentences = textReader(fileName);
        /**
         * Se crea el mapa binario donde se guardaran las palabras del diccionario.
		 * Se crea una lista de oraciones con la lista de palabras.
         */
		BinaryTree<Association<String, String>> binaryTree = new BinaryTree<>();
		ArrayList<ArrayList<Association<String, String>>> sentencesAssociations = new ArrayList<>();
		/**
		 * Comienza la lectura de los elementos del archivo en la lista para ingresarlos
		 * al BinaryTree.
		 */
        String[] traductions = new String[2];
        Association<String, String> node;
		for (String enAndSpa : listDictionary) {
			traductions[0] = enAndSpa.split(",")[0].replaceAll(" ", "");
            traductions[1] = enAndSpa.split(",")[1].replaceAll(" ", "");
			node = new Association<>(traductions[0].toLowerCase(), traductions[1]);
			binaryTree.addNode(node);
		}
		/**
		 * Se agregan las palabras a la lista de oraciones tambien como asociaciones
		 */
		int contSentence = 0;
		for (String sentence : sentences) {
			sentencesAssociations.add(new ArrayList<Association<String, String>>());
			String[] words = sentence.replaceAll("\\.","").split(" ");
			for (String word : words) {
				node = new Association<>(word.toLowerCase(), null);
				sentencesAssociations.get(contSentence).add(node);
			}
			contSentence++;
		}
		/**
		 * se recorren las lineas del texto *(oraciones) para traducir
		 */
		//binaryTree.printInOrder();
		String result = "";
		for (ArrayList<Association<String, String>> sentence : sentencesAssociations) {
			for (Association<String, String> wordToTranslate : sentence) {
				if (binaryTree.contains(wordToTranslate)) {
					result += String.valueOf(binaryTree.getNodeData(wordToTranslate).getValue());
				} else {
					result += "*" + wordToTranslate.getKey() + "*";
				}
				result += " ";
			}
			result = result.substring(0, result.length() - 1);
			result += ".";
			result += "\n";
		}
		/**
		 * Se imprime el resultado
		 */
		System.out.println(result);
		scan.close();
	}
}