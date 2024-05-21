package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Address {
	public long addressId;
	public String address;
	public String address2;
	public String district;
	public String postalCode;
	public String phone;
	public byte[] location;
	public LocalDateTime lastUpdate;

	public Collection<Customer> customer;
	public Collection<Staff> staff;
	public Collection<Store> store;
	public City city;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long newAddressId) {
		addressId = newAddressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String newAddress) {
		address = newAddress;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String newAddress2) {
		address2 = newAddress2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String newDistrict) {
		district = newDistrict;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String newPostalCode) {
		postalCode = newPostalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	public byte[] getLocation() {
		return location;
	}

	public void setLocation(byte[] newLocation) {
		location = newLocation;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public java.util.Collection<Customer> getCustomer() {
		if (customer == null)
			customer = new java.util.HashSet<Customer>();
		return customer;
	}

	public Iterator<Customer> getIteratorCustomer() {
		if (customer == null)
			customer = new java.util.HashSet<Customer>();
		return customer.iterator();
	}

	public void setCustomer(Collection<Customer> newCustomer) {
		removeAllCustomer();
		for (Iterator<Customer> iter = newCustomer.iterator(); iter.hasNext();)
			addCustomer((Customer) iter.next());
	}

	public void addCustomer(Customer newCustomer) {
		if (newCustomer == null)
			return;
		if (this.customer == null)
			this.customer = new java.util.HashSet<Customer>();
		if (!this.customer.contains(newCustomer)) {
			this.customer.add(newCustomer);
			newCustomer.setAddress(this);
		}
	}

	public void removeCustomer(Customer oldCustomer) {
		if (oldCustomer == null)
			return;
		if (this.customer != null)
			if (this.customer.contains(oldCustomer)) {
				this.customer.remove(oldCustomer);
				oldCustomer.setAddress((Address) null);
			}
	}

	public void removeAllCustomer() {
		if (customer != null) {
			Customer oldCustomer;
			for (Iterator<Customer> iter = getIteratorCustomer(); iter.hasNext();) {
				oldCustomer = (Customer) iter.next();
				iter.remove();
				oldCustomer.setAddress((Address) null);
			}
		}
	}

	public Collection<Staff> getStaff() {
		if (staff == null)
			staff = new java.util.HashSet<Staff>();
		return staff;
	}

	public Iterator<Staff> getIteratorStaff() {
		if (staff == null)
			staff = new HashSet<Staff>();
		return staff.iterator();
	}

	public void setStaff(Collection<Staff> newStaff) {
		removeAllStaff();
		for (Iterator<Staff> iter = newStaff.iterator(); iter.hasNext();)
			addStaff((Staff) iter.next());
	}

	public void addStaff(Staff newStaff) {
		if (newStaff == null)
			return;
		if (this.staff == null)
			this.staff = new java.util.HashSet<Staff>();
		if (!this.staff.contains(newStaff)) {
			this.staff.add(newStaff);
			newStaff.setAddress(this);
		}
	}

	public void removeStaff(Staff oldStaff) {
		if (oldStaff == null)
			return;
		if (this.staff != null)
			if (this.staff.contains(oldStaff)) {
				this.staff.remove(oldStaff);
				oldStaff.setAddress((Address) null);
			}
	}

	public void removeAllStaff() {
		if (staff != null) {
			Staff oldStaff;
			for (Iterator<Staff> iter = getIteratorStaff(); iter.hasNext();) {
				oldStaff = (Staff) iter.next();
				iter.remove();
				oldStaff.setAddress((Address) null);
			}
		}
	}

	public Collection<Store> getStore() {
		if (store == null)
			store = new java.util.HashSet<Store>();
		return store;
	}

	public Iterator<Store> getIteratorStore() {
		if (store == null)
			store = new java.util.HashSet<Store>();
		return store.iterator();
	}

	public void setStore(Collection<Store> newStore) {
		removeAllStore();
		for (Iterator<Store> iter = newStore.iterator(); iter.hasNext();)
			addStore((Store) iter.next());
	}

	public void addStore(Store newStore) {
		if (newStore == null)
			return;
		if (this.store == null)
			this.store = new java.util.HashSet<Store>();
		if (!this.store.contains(newStore)) {
			this.store.add(newStore);
			newStore.setAddress(this);
		}
	}

	public void removeStore(Store oldStore) {
		if (oldStore == null)
			return;
		if (this.store != null)
			if (this.store.contains(oldStore)) {
				this.store.remove(oldStore);
				oldStore.setAddress((Address) null);
			}
	}

	public void removeAllStore() {
		if (store != null) {
			Store oldStore;
			for (Iterator<Store> iter = getIteratorStore(); iter.hasNext();) {
				oldStore = (Store) iter.next();
				iter.remove();
				oldStore.setAddress((Address) null);
			}
		}
	}

	public City getCity() {
		return city;
	}

	public void setCity(City newCity) {
		if (this.city == null || !this.city.equals(newCity)) {
			if (this.city != null) {
				City oldCity = this.city;
				this.city = null;
				oldCity.removeAddress(this);
			}
			if (newCity != null) {
				this.city = newCity;
				this.city.addAddress(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Address o1 = (Address) o;
		return addressId == o1.addressId && Objects.equals(address, o1.address) && Objects.equals(address2, o1.address2)
				&& Objects.equals(district, o1.district) && Objects.equals(postalCode, o1.postalCode)
				&& Objects.equals(phone, o1.phone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId, address, address2, district, postalCode, phone);
	}

	@Override
	public String toString() {
		return "Address{" + "addressId=" + addressId + ", address='" + address + '\'' + ", address2='" + address2 + '\''
				+ ", district='" + district + '\'' + ", postalCode='" + postalCode + '\'' + ", phone='" + phone + '\''
				+ ", lastUpdate=" + lastUpdate + '}';
	}
}