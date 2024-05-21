package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class FilmCategory {
	public LocalDateTime lastUpdate;
	public Category category;
	public Film film;

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FilmActor o1 = (FilmActor) o;
		return Objects.equals(lastUpdate, o1.lastUpdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastUpdate);
	}

	@Override
	public String toString() {
		return "FilmActor{" + "lastUpdate=" + lastUpdate + '}';
	}
}