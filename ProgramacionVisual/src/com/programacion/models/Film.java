package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

enum Rating {
	G("G"), PG("PG"), PG_13("PG-13"), R("R"), NC_17("NC-17");

	private String label;

	private Rating(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}
}

enum SpecialFeatures {
	TRAILERS("Trailers"), COMMENTARIES("Commentaries"), DELETED_SCENES("Deleted Scenes"),
	BEHIND_THE_SCENES("Behind the Scenes");

	private String label;

	private SpecialFeatures(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}
}

public class Film {
	public long filmId;
	public String title;
	public String description;
	public short releaseYear;
	public byte rentalDuration = 3;
	public double rentalRate = 4.99;
	public short length;
	public double replacementCost = 19.99;
	public Rating rating;
	public SpecialFeatures specialFeatures;
	public LocalDateTime lastUpdate;

	public Collection<Inventory> inventory;
	public Collection<FilmActor> filmActor;
	public Collection<FilmCategory> filmCategory;
	public Language language;

	public long getFilmId() {
		return filmId;
	}

	public void setFilmId(long newFilmId) {
		filmId = newFilmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short newReleaseYear) {
		releaseYear = newReleaseYear;
	}

	public byte getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(byte newRentalDuration) {
		rentalDuration = newRentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double newRentalRate) {
		rentalRate = newRentalRate;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short newLength) {
		length = newLength;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double newReplacementCost) {
		replacementCost = newReplacementCost;
	}

	public String getRating() {
		return rating.getLabel();
	}

	public void setRating(Rating newRating) {
		rating = newRating;
	}

	public String getSpecialFeatures() {
		return specialFeatures.getLabel();
	}

	public void setSpecialFeatures(SpecialFeatures newSpecialFeatures) {
		specialFeatures = newSpecialFeatures;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Collection<Inventory> getInventory() {
		if (inventory == null)
			inventory = new HashSet<Inventory>();
		return inventory;
	}

	public Iterator<Inventory> getIteratorInventory() {
		if (inventory == null)
			inventory = new HashSet<Inventory>();
		return inventory.iterator();
	}

	public void setInventory(Collection<Inventory> newInventory) {
		removeAllInventory();
		for (Iterator<Inventory> iter = newInventory.iterator(); iter.hasNext();)
			addInventory((Inventory) iter.next());
	}

	public void addInventory(Inventory newInventory) {
		if (newInventory == null)
			return;
		if (this.inventory == null)
			this.inventory = new HashSet<Inventory>();
		if (!this.inventory.contains(newInventory)) {
			this.inventory.add(newInventory);
			newInventory.setFilm(this);
		}
	}

	public void removeInventory(Inventory oldInventory) {
		if (oldInventory == null)
			return;
		if (this.inventory != null)
			if (this.inventory.contains(oldInventory)) {
				this.inventory.remove(oldInventory);
				oldInventory.setFilm((Film) null);
			}
	}

	public void removeAllInventory() {
		if (inventory != null) {
			Inventory oldInventory;
			for (Iterator<Inventory> iter = getIteratorInventory(); iter.hasNext();) {
				oldInventory = (Inventory) iter.next();
				iter.remove();
				oldInventory.setFilm((Film) null);
			}
		}
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language newLanguage) {
		if (this.language == null || !this.language.equals(newLanguage)) {
			if (this.language != null) {
				Language oldLanguage = this.language;
				this.language = null;
				oldLanguage.removeFilm(this);
			}
			if (newLanguage != null) {
				this.language = newLanguage;
				this.language.addFilm(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Film o1 = (Film) o;
		return filmId == o1.filmId && Objects.equals(title, o1.title) && Objects.equals(description, o1.description)
				&& Objects.equals(releaseYear, o1.releaseYear) && Objects.equals(rentalDuration, o1.rentalDuration)
				&& Objects.equals(rentalRate, o1.rentalRate) && Objects.equals(length, o1.length)
				&& Objects.equals(replacementCost, o1.replacementCost) && Objects.equals(rating, o1.rating)
				&& Objects.equals(specialFeatures, o1.specialFeatures);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filmId, title, description, releaseYear, rentalDuration, rentalRate, length,
				replacementCost, rating, specialFeatures);
	}

	@Override
	public String toString() {
		return "Film{" + "filmId=" + filmId + ", title='" + title + '\'' + ", description='" + description + '\''
				+ ", releaseYear='" + releaseYear + '\'' + ", rentalDuration='" + rentalDuration + '\''
				+ ", rentalRate='" + rentalRate + '\'' + ", length='" + length + '\'' + ", replacementCost='"
				+ replacementCost + '\'' + ", rating='" + rating + '\'' + ", specialFeatures=" + specialFeatures + '\''
				+ ", lastUpdate=" + lastUpdate + '}';
	}
}