package com.programacion.dos;

//Interfaz que define los métodos para gestionar una reserva
interface Reserva {
	void reservar();

	void cancelar();

	void consultar();
}

//Clase abstracta que proporciona una implementación básica de los métodos de la interfaz Reserva
abstract class AbstractReserva implements Reserva {
	private boolean reservada;

	@Override
	public void reservar() {
		if (!reservada) {
			reservada = true;
			System.out.println("Reserva realizada exitosamente.");
		} else {
			System.out.println("La reserva ya ha sido realizada.");
		}
	}

	@Override
	public void cancelar() {
		if (reservada) {
			reservada = false;
			System.out.println("Reserva cancelada.");
		} else {
			System.out.println("No hay reserva para cancelar.");
		}
	}

	@Override
	public void consultar() {
		if (reservada) {
			System.out.println("Hay una reserva realizada.");
		} else {
			System.out.println("No hay ninguna reserva realizada.");
		}
	}
}

//Clase concreta que gestiona las reservas en un hotel
class HotelReserva extends AbstractReserva {
	private String nombreHotel;
	private String fechaInicio;
	private String fechaFin;

	public HotelReserva(String nombreHotel, String fechaInicio, String fechaFin) {
		this.nombreHotel = nombreHotel;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	@Override
	public void consultar() {
		super.consultar();
		System.out.println("Nombre del hotel: " + nombreHotel);
		System.out.println("Fecha de inicio de la reserva: " + fechaInicio);
		System.out.println("Fecha de fin de la reserva: " + fechaFin);
	}
}

public class Ejercicio03 {
	public static void main(String[] args) {
		// Ejemplo de uso
		HotelReserva reservaHotel = new HotelReserva("Hotel ABC", "2024-03-25", "2024-03-28");
		reservaHotel.consultar();
		reservaHotel.reservar();
		reservaHotel.consultar();
		reservaHotel.cancelar();
		reservaHotel.consultar();
	}
}
