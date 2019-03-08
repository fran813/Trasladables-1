package org.iesalandalus.programacion.trasladable;

import java.util.Random;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.trasladable.modelo.dao.Trasladables;
import org.iesalandalus.programacion.trasladable.modelo.dominio.Circunferencia;
import org.iesalandalus.programacion.trasladable.modelo.dominio.Linea;
import org.iesalandalus.programacion.trasladable.modelo.dominio.Punto;
import org.iesalandalus.programacion.trasladable.modelo.dominio.Trasladable;

public class MainApp {
	
	private static final double LIMITE = 100d;
	private static final Random GENERADOR = new Random();
	
	private static Trasladables modelo;
	
	public static void main(String[] args) {
		System.out.println("Tarea presencial PROG05-08 2018/2019");
		
		modelo = new Trasladables();
		modelo.leer();
		mostrarTrasladables();
		System.out.println("Genero nuevos trasladables....");
		generarInsertarTrasladables();
		mostrarTrasladables();
		System.out.println("Traslado todos los trasladables habidos y los inserto.....");
		trasladar();
		mostrarTrasladables();
		modelo.escribir();
	}
	
	private static void mostrarTrasladables() {
		for (String cadena : modelo.representar()) {
			System.out.println(cadena);
		}
	}
	
	private static void generarInsertarTrasladables() {
		Punto p = generarPunto();
		try {
			modelo.insertar(p);
		} catch (OperationNotSupportedException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		Linea l = new Linea(generarPunto(), generarPunto());
		try {
			modelo.insertar(l);
		} catch (OperationNotSupportedException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		Circunferencia c = new Circunferencia(generarPunto(), GENERADOR.nextDouble() * LIMITE);
		try {
			modelo.insertar(c);
		} catch (OperationNotSupportedException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static Punto generarPunto() {
		return new Punto(GENERADOR.nextDouble() * LIMITE, GENERADOR.nextDouble() * LIMITE);
	}
	
	private static void trasladar() {
		for (Trasladable trasladable : modelo.getTrasladables()) {
			trasladable.trasladar(GENERADOR.nextDouble() * LIMITE, GENERADOR.nextDouble() * LIMITE);
			try {
				modelo.insertar(trasladable);

			} catch (OperationNotSupportedException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
	}
	
}
