package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class FilmActor {
	public LocalDateTime lastUpdate;
	public Actor actor;
	public Film frilm;

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