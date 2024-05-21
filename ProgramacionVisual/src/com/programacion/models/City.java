package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class City {
	public long cityId;
	public String city;
	public LocalDateTime lastUpdate;
	public Collection<Address> address;
	public Country country;

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long newCityId) {
		cityId = newCityId;
	}

	public java.lang.String getCity() {
		return city;
	}

	public void setCity(String newCity) {
		city = newCity;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Collection<Address> getAddress() {
		if (address == null)
			address = new java.util.HashSet<Address>();
		return address;
	}

	public Iterator<Address> getIteratorAddress() {
		if (address == null)
			address = new java.util.HashSet<Address>();
		return address.iterator();
	}

	public void setAddress(java.util.Collection<Address> newAddress) {
		removeAllAddress();
		for (Iterator<Address> iter = newAddress.iterator(); iter.hasNext();)
			addAddress((Address) iter.next());
	}

	public void addAddress(Address newAddress) {
		if (newAddress == null)
			return;
		if (this.address == null)
			this.address = new java.util.HashSet<Address>();
		if (!this.address.contains(newAddress)) {
			this.address.add(newAddress);
			newAddress.setCity(this);
		}
	}

	public void removeAddress(Address oldAddress) {
		if (oldAddress == null)
			return;
		if (this.address != null)
			if (this.address.contains(oldAddress)) {
				this.address.remove(oldAddress);
				oldAddress.setCity((City) null);
			}
	}

	public void removeAllAddress() {
		if (address != null) {
			Address oldAddress;
			for (Iterator<Address> iter = getIteratorAddress(); iter.hasNext();) {
				oldAddress = (Address) iter.next();
				iter.remove();
				oldAddress.setCity((City) null);
			}
		}
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country newCountry) {
		if (this.country == null || !this.country.equals(newCountry)) {
			if (this.country != null) {
				Country oldCountry = this.country;
				this.country = null;
				oldCountry.removeCity(this);
			}
			if (newCountry != null) {
				this.country = newCountry;
				this.country.addCity(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		City o1 = (City) o;
		return cityId == o1.cityId && Objects.equals(city, o1.city);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityId, city);
	}

	@Override
	public String toString() {
		return "City{" + "cityId=" + cityId + ", city='" + city + '\'' + ", lastUpdate=" + lastUpdate + '}';
	}
}