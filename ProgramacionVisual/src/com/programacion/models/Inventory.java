package com.programacion.models;

import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Inventory {
	public long inventoryId;
	public Date lastUpdate;
	public Collection<Rental> rental;
	public Film film;
	public Store store;

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long newInventoryId) {
		inventoryId = newInventoryId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Collection<Rental> getRental() {
		if (rental == null)
			rental = new HashSet<Rental>();
		return rental;
	}

	public Iterator<Rental> getIteratorRental() {
		if (rental == null)
			rental = new HashSet<Rental>();
		return rental.iterator();
	}

	public void setRental(Collection<Rental> newRental) {
		removeAllRental();
		for (Iterator<Rental> iter = newRental.iterator(); iter.hasNext();)
			addRental((Rental) iter.next());
	}

	public void addRental(Rental newRental) {
		if (newRental == null)
			return;
		if (this.rental == null)
			this.rental = new HashSet<Rental>();
		if (!this.rental.contains(newRental)) {
			this.rental.add(newRental);
			newRental.setInventory(this);
		}
	}

	public void removeRental(Rental oldRental) {
		if (oldRental == null)
			return;
		if (this.rental != null)
			if (this.rental.contains(oldRental)) {
				this.rental.remove(oldRental);
				oldRental.setInventory((Inventory) null);
			}
	}

	public void removeAllRental() {
		if (rental != null) {
			Rental oldRental;
			for (Iterator<Rental> iter = getIteratorRental(); iter.hasNext();) {
				oldRental = (Rental) iter.next();
				iter.remove();
				oldRental.setInventory((Inventory) null);
			}
		}
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film newFilm) {
		if (this.film == null || !this.film.equals(newFilm)) {
			if (this.film != null) {
				Film oldFilm = this.film;
				this.film = null;
				oldFilm.removeInventory(this);
			}
			if (newFilm != null) {
				this.film = newFilm;
				this.film.addInventory(this);
			}
		}
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store newStore) {
		if (this.store == null || !this.store.equals(newStore)) {
			if (this.store != null) {
				Store oldStore = this.store;
				this.store = null;
				oldStore.removeInventory(this);
			}
			if (newStore != null) {
				this.store = newStore;
				this.store.addInventory(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Inventory o1 = (Inventory) o;
		return inventoryId == o1.inventoryId && Objects.equals(lastUpdate, o1.lastUpdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(inventoryId, lastUpdate);
	}

	@Override
	public String toString() {
		return "Inventory{" + "inventoryId=" + inventoryId + ", lastUpdate=" + lastUpdate + '}';
	}
}