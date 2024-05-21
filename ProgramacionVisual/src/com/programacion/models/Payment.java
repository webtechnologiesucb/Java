package com.programacion.models;

import java.util.Date;
import java.util.Objects;

public class Payment {
	public long paymentId;
	public double amount;
	public Date paymentDate;
	public Date lastUpdate;
	public Customer customer;
	public Rental rental;
	public Staff staff;

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long newPaymentId) {
		paymentId = newPaymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double newAmount) {
		amount = newAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date newPaymentDate) {
		paymentDate = newPaymentDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer newCustomer) {
		if (this.customer == null || !this.customer.equals(newCustomer)) {
			if (this.customer != null) {
				Customer oldCustomer = this.customer;
				this.customer = null;
				oldCustomer.removePayment(this);
			}
			if (newCustomer != null) {
				this.customer = newCustomer;
				this.customer.addPayment(this);
			}
		}
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental newRental) {
		if (this.rental == null || !this.rental.equals(newRental)) {
			if (this.rental != null) {
				Rental oldRental = this.rental;
				this.rental = null;
				oldRental.removePayment(this);
			}
			if (newRental != null) {
				this.rental = newRental;
				this.rental.addPayment(this);
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
				oldStaff.removePayment(this);
			}
			if (newStaff != null) {
				this.staff = newStaff;
				this.staff.addPayment(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Payment payment = (Payment) o;
		return paymentId == payment.paymentId && Double.compare(payment.amount, amount) == 0
				&& Objects.equals(paymentDate, payment.paymentDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(paymentId, amount, paymentDate);
	}

	@Override
	public String toString() {
		return "Payment{" + "paymentId=" + paymentId + ", amount=" + amount + ", paymentDate=" + paymentDate
				+ ", lastUpdate=" + lastUpdate + '}';
	}

}