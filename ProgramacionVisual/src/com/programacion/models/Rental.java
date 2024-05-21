package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Rental {
	public long rentalId;
	public LocalDateTime rentalDate;
	public int inventoryId;
	public short customerId;
	public LocalDateTime returnDate;
	public LocalDateTime lastUpdate;
	public Collection<Payment> payment;
	public Customer customer;
	public Inventory inventory;
	public Staff staff;

	public long getRentalId() {
		return rentalId;
	}

	public void setRentalId(long newRentalId) {
		rentalId = newRentalId;
	}

	public LocalDateTime getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDateTime newRentalDate) {
		rentalDate = newRentalDate;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int newInventoryId) {
		inventoryId = newInventoryId;
	}

	public short getCustomerId() {
		return customerId;
	}

	public void setCustomerId(short newCustomerId) {
		customerId = newCustomerId;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime newReturnDate) {
		returnDate = newReturnDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Collection<Payment> getPayment() {
		if (payment == null)
			payment = new HashSet<Payment>();
		return payment;
	}

	public Iterator<Payment> getIteratorPayment() {
		if (payment == null)
			payment = new HashSet<Payment>();
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
			this.payment = new HashSet<Payment>();
		if (!this.payment.contains(newPayment)) {
			this.payment.add(newPayment);
			newPayment.setRental(this);
		}
	}

	public void removePayment(Payment oldPayment) {
		if (oldPayment == null)
			return;
		if (this.payment != null)
			if (this.payment.contains(oldPayment)) {
				this.payment.remove(oldPayment);
				oldPayment.setRental((Rental) null);
			}
	}

	public void removeAllPayment() {
		if (payment != null) {
			Payment oldPayment;
			for (Iterator<Payment> iter = getIteratorPayment(); iter.hasNext();) {
				oldPayment = (Payment) iter.next();
				iter.remove();
				oldPayment.setRental((Rental) null);
			}
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer newCustomer) {
		if (this.customer == null || !this.customer.equals(newCustomer)) {
			if (this.customer != null) {
				Customer oldCustomer = this.customer;
				this.customer = null;
				oldCustomer.removeRental(this);
			}
			if (newCustomer != null) {
				this.customer = newCustomer;
				this.customer.addRental(this);
			}
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory newInventory) {
		if (this.inventory == null || !this.inventory.equals(newInventory)) {
			if (this.inventory != null) {
				Inventory oldInventory = this.inventory;
				this.inventory = null;
				oldInventory.removeRental(this);
			}
			if (newInventory != null) {
				this.inventory = newInventory;
				this.inventory.addRental(this);
			}
		}
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff newStaff) {
		if (this.staff == null || !this.staff.equals(newStaff)) {
			if (this.staff != null) {
				Staff oldStaff = this.staff;
				this.staff = null;
				oldStaff.removeRental(this);
			}
			if (newStaff != null) {
				this.staff = newStaff;
				this.staff.addRental(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Rental o1 = (Rental) o;
		return rentalId == o1.rentalId && inventoryId == o1.inventoryId && customerId == o1.customerId
				&& Objects.equals(rentalDate, o1.rentalDate) && Objects.equals(returnDate, o1.returnDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rentalId, rentalDate, inventoryId, customerId, returnDate);
	}

	@Override
	public String toString() {
		return "Rental{" + "rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", inventoryId=" + inventoryId
				+ ", customerId=" + customerId + ", returnDate=" + returnDate + ", lastUpdate=" + lastUpdate + '}';
	}

}