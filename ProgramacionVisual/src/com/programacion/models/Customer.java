package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Customer {
	public long customerId;
	public String firstName;
	public String lastName;
	public String email;
	public long active = 1;
	public LocalDateTime createDate;
	public LocalDateTime lastUpdate;
	public Collection<Payment> payment;
	public Collection<Rental> rental;
	public Address address;
	public Store store;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long newCustomerId) {
		customerId = newCustomerId;
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime newCreateDate) {
		createDate = newCreateDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public java.util.Collection<Payment> getPayment() {
		if (payment == null)
			payment = new java.util.HashSet<Payment>();
		return payment;
	}

	public Iterator<Payment> getIteratorPayment() {
		if (payment == null)
			payment = new java.util.HashSet<Payment>();
		return payment.iterator();
	}

	public void setPayment(java.util.Collection<Payment> newPayment) {
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
			newPayment.setCustomer(this);
		}
	}

	public void removePayment(Payment oldPayment) {
		if (oldPayment == null)
			return;
		if (this.payment != null)
			if (this.payment.contains(oldPayment)) {
				this.payment.remove(oldPayment);
				oldPayment.setCustomer((Customer) null);
			}
	}

	public void removeAllPayment() {
		if (payment != null) {
			Payment oldPayment;
			for (Iterator<Payment> iter = getIteratorPayment(); iter.hasNext();) {
				oldPayment = (Payment) iter.next();
				iter.remove();
				oldPayment.setCustomer((Customer) null);
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
			newRental.setCustomer(this);
		}
	}

	public void removeRental(Rental oldRental) {
		if (oldRental == null)
			return;
		if (this.rental != null)
			if (this.rental.contains(oldRental)) {
				this.rental.remove(oldRental);
				oldRental.setCustomer((Customer) null);
			}
	}

	public void removeAllRental() {
		if (rental != null) {
			Rental oldRental;
			for (Iterator<Rental> iter = getIteratorRental(); iter.hasNext();) {
				oldRental = (Rental) iter.next();
				iter.remove();
				oldRental.setCustomer((Customer) null);
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
				oldAddress.removeCustomer(this);
			}
			if (newAddress != null) {
				this.address = newAddress;
				this.address.addCustomer(this);
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
				oldStore.removeCustomer(this);
			}
			if (newStore != null) {
				this.store = newStore;
				this.store.addCustomer(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer o1 = (Customer) o;
		return customerId == o1.customerId && Objects.equals(firstName, o1.firstName)
				&& Objects.equals(lastName, o1.lastName) && Objects.equals(email, o1.email)
				&& Objects.equals(active, o1.active);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, firstName, lastName, email, active);
	}

	@Override
	public String toString() {
		return "Customer{" + "customerId=" + customerId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
				+ '\'' + ", email='" + email + '\'' + ", active='" + active + '\'' + ", createDate='" + createDate
				+ '\'' + ", lastUpdate=" + lastUpdate + '}';
	}
}