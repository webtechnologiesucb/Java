package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Staff {
	public long staffId;
	public String firstName;
	public String lastName;
	public String picture;
	public String email;
	public long active = 1;
	public String username;
	public String password;
	public LocalDateTime lastUpdate;
	public java.util.Collection<Payment> payment;
	public java.util.Collection<Rental> rental;
	public Address address;
	public Store store;

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long newStaffId) {
		staffId = newStaffId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		lastName = newLastName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String newPicture) {
		picture = newPicture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String newEmail) {
		email = newEmail;
	}

	public long getActive() {
		return active;
	}

	public void setActive(long newActive) {
		active = newActive;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String newUsername) {
		username = newUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Collection<Payment> getPayment() {
		if (payment == null)
			payment = new java.util.HashSet<Payment>();
		return payment;
	}

	public Iterator<Payment> getIteratorPayment() {
		if (payment == null)
			payment = new java.util.HashSet<Payment>();
		return payment.iterator();
	}

	public void setPayment(Collection<Payment> newPayment) {
		removeAllPayment();
		for (Iterator<Payment> iter = newPayment.iterator(); iter.hasNext();)
			addPayment((Payment) iter.next());
	}

	public void addPayment(Payment newPayment) {
		if (newPayment == null)
			return;
		if (this.payment == null)
			this.payment = new java.util.HashSet<Payment>();
		if (!this.payment.contains(newPayment)) {
			this.payment.add(newPayment);
			newPayment.setStaff(this);
		}
	}

	public void removePayment(Payment oldPayment) {
		if (oldPayment == null)
			return;
		if (this.payment != null)
			if (this.payment.contains(oldPayment)) {
				this.payment.remove(oldPayment);
				oldPayment.setStaff((Staff) null);
			}
	}

	public void removeAllPayment() {
		if (payment != null) {
			Payment oldPayment;
			for (Iterator<Payment> iter = getIteratorPayment(); iter.hasNext();) {
				oldPayment = (Payment) iter.next();
				iter.remove();
				oldPayment.setStaff((Staff) null);
			}
		}
	}

	public java.util.Collection<Rental> getRental() {
		if (rental == null)
			rental = new java.util.HashSet<Rental>();
		return rental;
	}

	public Iterator<Rental> getIteratorRental() {
		if (rental == null)
			rental = new java.util.HashSet<Rental>();
		return rental.iterator();
	}

	public void setRental(java.util.Collection<Rental> newRental) {
		removeAllRental();
		for (Iterator<Rental> iter = newRental.iterator(); iter.hasNext();)
			addRental((Rental) iter.next());
	}

	public void addRental(Rental newRental) {
		if (newRental == null)
			return;
		if (this.rental == null)
			this.rental = new java.util.HashSet<Rental>();
		if (!this.rental.contains(newRental)) {
			this.rental.add(newRental);
			newRental.setStaff(this);
		}
	}

	public void removeRental(Rental oldRental) {
		if (oldRental == null)
			return;
		if (this.rental != null)
			if (this.rental.contains(oldRental)) {
				this.rental.remove(oldRental);
				oldRental.setStaff((Staff) null);
			}
	}

	public void removeAllRental() {
		if (rental != null) {
			Rental oldRental;
			for (Iterator<Rental> iter = getIteratorRental(); iter.hasNext();) {
				oldRental = (Rental) iter.next();
				iter.remove();
				oldRental.setStaff((Staff) null);
			}
		}
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address newAddress) {
		if (this.address == null || !this.address.equals(newAddress)) {
			if (this.address != null) {
				Address oldAddress = this.address;
				this.address = null;
				oldAddress.removeStaff(this);
			}
			if (newAddress != null) {
				this.address = newAddress;
				this.address.addStaff(this);
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
				oldStore.removeStaff(this);
			}
			if (newStore != null) {
				this.store = newStore;
				this.store.addStaff(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Staff o1 = (Staff) o;
		return staffId == o1.staffId && Objects.equals(username, o1.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(staffId, username);
	}

	@Override
	public String toString() {
		return "Staff{" + "staffId=" + staffId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", username='" + username + '\'' + ", lastUpdate=" + lastUpdate + '}';
	}

}