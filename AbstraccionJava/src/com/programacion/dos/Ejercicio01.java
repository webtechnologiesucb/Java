package com.programacion.dos;

import java.util.List;
import java.util.ArrayList;

//Interfaz ProductoDAO que define los métodos para realizar operaciones CRUD 
interface ProductoDAO {
	void crearProducto(Producto producto);

	Producto obtenerProducto(int id);

	List<Producto> obtenerTodosProductos();

	void actualizarProducto(Producto producto);

	void eliminarProducto(int id);
}

//Clase abstracta AbstractProductoDAO que proporciona implementaciones básicas 
abstract class AbstractProductoDAO implements ProductoDAO {
	@Override
	public void crearProducto(Producto producto) {
		// Implementación básica
	}

	@Override
	public Producto obtenerProducto(int id) {
		// Implementación básica
		return null;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// Implementación básica
		return null;
	}

	@Override
	public void actualizarProducto(Producto producto) {
		// Implementación básica
	}

	@Override
	public void eliminarProducto(int id) {
		// Implementación básica
	}
}

//Clase concreta ProductoDAOImpl que implementa la interfaz ProductoDAO y realiza operaciones CRUD 
class ProductoDAOImpl extends AbstractProductoDAO {
	// Simulación de una lista de productos
	private List<Producto> productos;

	public ProductoDAOImpl() {
		productos = new ArrayList<>();
	}

	@Override
	public void crearProducto(Producto producto) {
		productos.add(producto);
	}

	@Override
	public Producto obtenerProducto(int id) {
		for (Producto producto : productos) {
			if (producto.getId() == id) {
				return producto;
			}
		}
		return null;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		return productos;
	}

	@Override
	public void actualizarProducto(Producto producto) {
		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).getId() == producto.getId()) {
				productos.set(i, producto);
				break;
			}
		}
	}

	@Override
	public void eliminarProducto(int id) {
		productos.removeIf(producto -> producto.getId() == id);
	}
}

//Clase Producto que representa un producto con un ID y un nombre
class Producto {
	private int id;
	private String nombre;

	public Producto(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

//Ejemplo de uso
public class Ejercicio01 {
	public static void main(String[] args) {
		// Crear instancia de la implementación de ProductoDAO con una base de datos
		// simulada
		ProductoDAO productoDAO = new ProductoDAOImpl();
		Producto p;
		for (int i = 1; i <= 4; i++) {
			p = new Producto(i, "Producto " + i);
			productoDAO.crearProducto(p);
		}
		// Ejemplo de uso de los métodos CRUD
		System.out.println("Productos antes de agregar uno nuevo:");
		imprimirProductos(productoDAO.obtenerTodosProductos());

		// Crear un nuevo producto
		Producto nuevoProducto = new Producto(5, "Producto 5");
		productoDAO.crearProducto(nuevoProducto);
		System.out.println("\nProductos después de agregar uno nuevo:");
		imprimirProductos(productoDAO.obtenerTodosProductos());

		// Obtener un producto por su ID
		System.out.println("\nObtener producto por ID (ID=2):");
		Producto producto = productoDAO.obtenerProducto(2);
		if (producto != null) {
			System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre());
		} else {
			System.out.println("No se encontró el producto con ID=2");
		}

		// Actualizar un producto existente
		if (producto != null) {
			producto.setNombre("Producto Actualizado");
			productoDAO.actualizarProducto(producto);
			System.out.println("\nProducto actualizado:");
			imprimirProductos(productoDAO.obtenerTodosProductos());
		}

		// Eliminar un producto por su ID
		productoDAO.eliminarProducto(1);
		System.out.println("\nProductos después de eliminar uno:");
		imprimirProductos(productoDAO.obtenerTodosProductos());
	}

	// Método auxiliar para imprimir la lista de productos
	private static void imprimirProductos(List<Producto> productos) {
		for (Producto producto : productos) {
			System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre());
		}
	}
}
