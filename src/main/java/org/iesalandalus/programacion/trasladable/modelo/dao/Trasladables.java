package org.iesalandalus.programacion.trasladable.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.trasladable.modelo.dominio.Circunferencia;
import org.iesalandalus.programacion.trasladable.modelo.dominio.Linea;
import org.iesalandalus.programacion.trasladable.modelo.dominio.Punto;
import org.iesalandalus.programacion.trasladable.modelo.dominio.Trasladable;

public class Trasladables {
	
	private static final String NOMBRE_FICHERO_TRASLADABLES = "ficheros/trasladables.dat";
	
	private List<Trasladable> coleccionTrasladables;
	
	public Trasladables() {
		coleccionTrasladables = new ArrayList<>();
	}
	
	public Trasladables(Trasladables trasladables) {
		setTrasladables(trasladables);
	}
	
	private void setTrasladables(Trasladables trasladables) {
		if (trasladables == null) {
			throw new NullPointerException("No se pueden copiar trasladables nulos.");
		}
		coleccionTrasladables = copiaProfundaTrasladables(trasladables.coleccionTrasladables);
	}
	
	private List<Trasladable> copiaProfundaTrasladables(List<Trasladable> trasladables) {
		List<Trasladable> otrosTrasladables = new ArrayList<>();
		for (Trasladable trasladable: trasladables) {
			if (trasladable instanceof Punto) {
				otrosTrasladables.add(new Punto((Punto)trasladable));
			} else if (trasladable instanceof Linea) {
				otrosTrasladables.add(new Linea((Linea)trasladable));
			} else if (trasladable instanceof Circunferencia) {
				otrosTrasladables.add(new Circunferencia((Circunferencia)trasladable));
			}
			
		}
		return otrosTrasladables;
	}
	
	public List<Trasladable> getTrasladables() {
		return copiaProfundaTrasladables(coleccionTrasladables);
	}
	
	public void insertar(Trasladable trasladable) throws OperationNotSupportedException {
		if (trasladable == null) {
			throw new OperationNotSupportedException("No se puede insertar un objeto trasladable nulo.");
		}
		if (coleccionTrasladables.contains(trasladable)) {
			throw new OperationNotSupportedException("El objeto trasladable a insertar ya existe.");
		} else {
			if (trasladable instanceof Punto) {
				coleccionTrasladables.add(new Punto((Punto)trasladable));
			} else if (trasladable instanceof Linea) {
				coleccionTrasladables.add(new Linea((Linea)trasladable));
			} else if (trasladable instanceof Circunferencia) {
				coleccionTrasladables.add(new Circunferencia((Circunferencia)trasladable));
			}
		}
	}
	
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Trasladable trasladable : coleccionTrasladables) {
			representacion.add(trasladable.toString());
		}
		return representacion;
	}
	
	public void leer() {
		File ficheroTrasladables = new File(NOMBRE_FICHERO_TRASLADABLES);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroTrasladables))) {
			Trasladable trasladable = null;
			do {
				trasladable = (Trasladable) entrada.readObject();
				insertar(trasladable);
			} while (trasladable != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de trasladables.");
		} catch (EOFException e) {
			System.out.println("Fichero trasladables leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void escribir() {
		File ficheroTrasladables = new File(NOMBRE_FICHERO_TRASLADABLES);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroTrasladables))){
			for (Trasladable trasladable : coleccionTrasladables) {
				salida.writeObject(trasladable);
			}
			System.out.println("Fichero trasladables escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de trasladables");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

}
