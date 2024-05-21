package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Country {
	public long countryId;
	public String country;
	public LocalDateTime lastUpdate;
	public Collection<City> city;

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long newCountryId) {
		countryId = newCountryId;
	}

	public java.lang.String getCountry() {
		return country;
	}

	public void setCountry(java.lang.String newCountry) {
		country = newCountry;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Collection<City> getCity() {
		if (city == null)
			city = new HashSet<City>();
		return city;
	}

	public Iterator<City> getIteratorCity() {
		if (city == null)
			city = new HashSet<City>();
		return city.iterator();
	}

	public void setCity(Collection<City> newCity) {
		removeAllCity();
		for (Iterator<City> iter = newCity.iterator(); iter.hasNext();)
			addCity((City) iter.next());
	}

	public void addCity(City newCity) {
		if (newCity == null)
			return;
		if (this.city == null)
			this.city = new HashSet<City>();
		if (!this.city.contains(newCity)) {
			this.city.add(newCity);
			newCity.setCountry(this);
		}
	}

	public void removeCity(City oldCity) {
		if (oldCity == null)
			return;
		if (this.city != null)
			if (this.city.contains(oldCity)) {
				this.city.remove(oldCity);
				oldCity.setCountry((Country) null);
			}
	}

	public void removeAllCity() {
		if (city != null) {
			City oldCity;
			for (Iterator<City> iter = getIteratorCity(); iter.hasNext();) {
				oldCity = (City) iter.next();
				iter.remove();
				oldCity.setCountry((Country) null);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Country o1 = (Country) o;
		return countryId == o1.countryId && Objects.equals(country, o1.country);
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryId, country);
	}

	@Override
	public String toString() {
		return "Country{" + "countryId=" + countryId + ", country='" + country + '\'' + ", lastUpdate=" + lastUpdate
				+ '}';
	}
}