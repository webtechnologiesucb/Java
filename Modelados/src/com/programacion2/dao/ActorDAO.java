package com.programacion2.dao;

import com.programacion2.modelo.Actor;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sakila";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	// Obtener todos los actores
	public List<Actor> getAllActors() throws SQLException {
		List<Actor> actors = new ArrayList<>();
		String query = "SELECT * FROM actor";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				actors.add(new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("last_update")));
			}
		}
		return actors;
	}

	// Insertar un actor
	public void insertActor(Actor actor) throws SQLException {
		String query = "INSERT INTO actor (first_name, last_name, last_update) VALUES (?, ?, NOW())";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, actor.getFirstName());
			pstmt.setString(2, actor.getLastName());
			pstmt.executeUpdate();
		}
	}

	// Actualizar un actor
	public void updateActor(Actor actor) throws SQLException {
		String query = "UPDATE actor SET first_name = ?, last_name = ?, last_update = NOW() WHERE actor_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, actor.getFirstName());
			pstmt.setString(2, actor.getLastName());
			pstmt.setInt(3, actor.getActorId());
			pstmt.executeUpdate();
		}
	}

	// Eliminar un actor
	public void deleteActor(int actorId) throws SQLException {
		String query = "DELETE FROM actor WHERE actor_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, actorId);
			pstmt.executeUpdate();
		}
	}
}
