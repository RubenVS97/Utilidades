/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author diabl
 */
public class Utilidades {
    // metodo para utilizar scanner con int
	public int leerDatosInt() {
		Scanner t = new Scanner(System.in);
		boolean esCorrecto = true;
		int numero = 0;
		do {
			esCorrecto = true;
			try {
				numero = t.nextInt();

			} catch (InputMismatchException ime) {
				System.out.println("No es correcto!");
				t.nextLine();
				esCorrecto = !esCorrecto;
			}

		} while (!esCorrecto);

		return numero;
	}

	// metodo para utilizar scanner con String
	public String leerDatosString() {
		Scanner t = new Scanner(System.in);

		String palabra = t.nextLine();

		return palabra;
	}
        
        public void escrituraJson(ArrayList<App> lista) throws IOException {
        File directorio = new File("./appsjson");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                ObjectMapper mapeador = new ObjectMapper();

                mapeador.registerModule(new JavaTimeModule());

                mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

                mapeador.writeValue(new File("appsjson/aplicacionesxml.json"), lista);

            }
        }
    }

    public void escrituraCsv(ArrayList<Profe> lista) {
        String idFichero = "No_Hay_Cursos_Con_Esas_Iniciales.csv";
        if (!lista.isEmpty()) {
            idFichero = lista.get(0).getCurso() + ".csv";
        }
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {

            for (int i = 0; i < lista.size(); i++) {
                flujo.write(lista.get(i).toString() + "\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    //metodo para quitar comillas de los archivos
    private static String quitarComillas(String s) {
        String quitar = s.substring(1, s.length() - 1);
        return quitar;
    }
    
    
    //copiar archivos
    public void crearCarpetaCopia() {
        Path directory = Paths.get("./copias");
        try {

            Files.createDirectory(directory);

        } catch (FileAlreadyExistsException faee) {

            System.out.println("No se puede crear ./copias porque ya existe");

        } catch (AccessDeniedException ade) {

            System.out.println("No tiene permisos para crear ./copias");

        } catch (IOException ioe) {

            System.out.println("Problema creando el directorio./copias");
            System.out.println("Seguramente la ruta está mal escrita o no existe");

        }
    }

    public void copiarFicheroTxt() {
        Path origen = Paths.get("./appstxt/aplicacionestxt.txt");
        Path destino = Paths.get("./copias/copiatxt.txt");

        try {

            Files.copy(origen, destino);

        } catch (IOException e) {
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());
        }
    }

    public void copiarFicheroXml() {
        Path origen = Paths.get("./appsxml/aplicacionesxml.xml");
        Path destino = Paths.get("./copias/copiaxml.xml");

        try {

            Files.copy(origen, destino);

        } catch (IOException e) {
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());

        }
    }

    public void copiarFicheroJson() {
        Path origen = Paths.get("appsjson/aplicacionesxml.json");
        Path destino = Paths.get("./copias/copiajson.json");

        try {

            Files.copy(origen, destino);

        } catch (IOException e) {
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());

        }
    }
}
