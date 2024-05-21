package com.programacion.models;

import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Language {
	public long languageId;
	public String name;
	public Date lastUpdate;
	public Collection<Film> film;

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long newLanguageId) {
		languageId = newLanguageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public java.util.Collection<Film> getFilm() {
		if (film == null)
			film = new java.util.HashSet<Film>();
		return film;
	}

	public Iterator<Film> getIteratorFilm() {
		if (film == null)
			film = new java.util.HashSet<Film>();
		return film.iterator();
	}

	public void setFilm(java.util.Collection<Film> newFilm) {
		removeAllFilm();
		for (Iterator<Film> iter = newFilm.iterator(); iter.hasNext();)
			addFilm((Film) iter.next());
	}

	public void addFilm(Film newFilm) {
		if (newFilm == null)
			return;
		if (this.film == null)
			this.film = new java.util.HashSet<Film>();
		if (!this.film.contains(newFilm)) {
			this.film.add(newFilm);
			newFilm.setLanguage(this);
		}
	}

	public void removeFilm(Film oldFilm) {
		if (oldFilm == null)
			return;
		if (this.film != null)
			if (this.film.contains(oldFilm)) {
				this.film.remove(oldFilm);
				oldFilm.setLanguage((Language) null);
			}
	}

	public void removeAllFilm() {
		if (film != null) {
			Film oldFilm;
			for (Iterator<Film> iter = getIteratorFilm(); iter.hasNext();) {
				oldFilm = (Film) iter.next();
				iter.remove();
				oldFilm.setLanguage((Language) null);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Language o1 = (Language) o;
		return languageId == o1.languageId && Objects.equals(name, o1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(languageId, name);
	}

	@Override
	public String toString() {
		return "Language{" + "languageId=" + languageId + ", name='" + name + '\'' + ", lastUpdate=" + lastUpdate + '}';
	}

}