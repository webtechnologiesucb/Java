package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Store {
	public long storeId;
	public byte managerStaffId;
	public LocalDateTime lastUpdate;
	public Collection<Customer> customer;
	public Collection<Inventory> inventory;
	public Collection<Staff> staff;
	public Address address;

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long newStoreId) {
		storeId = newStoreId;
	}

	public byte getManagerStaffId() {
		return managerStaffId;
	}

	public void setManagerStaffId(byte newManagerStaffId) {
		managerStaffId = newManagerStaffId;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	public Collection<Customer> getCustomer() {
		if (customer == null)
			customer = new HashSet<Customer>();
		return customer;
	}

	public Iterator<Customer> getIteratorCustomer() {
		if (customer == null)
			customer = new HashSet<Customer>();
		return customer.iterator();
	}

	public void setCustomer(java.util.Collection<Customer> newCustomer) {
		removeAllCustomer();
		for (Iterator<Customer> iter = newCustomer.iterator(); iter.hasNext();)
			addCustomer((Customer) iter.next());
	}

	public void addCustomer(Customer newCustomer) {
		if (newCustomer == null)
			return;
		if (this.customer == null)
			this.customer = new HashSet<Customer>();
		if (!this.customer.contains(newCustomer)) {
			this.customer.add(newCustomer);
			newCustomer.setStore(this);
		}
	}

	public void removeCustomer(Customer oldCustomer) {
		if (oldCustomer == null)
			return;
		if (this.customer != null)
			if (this.customer.contains(oldCustomer)) {
				this.customer.remove(oldCustomer);
				oldCustomer.setStore((Store) null);
			}
	}

	public void removeAllCustomer() {
		if (customer != null) {
			Customer oldCustomer;
			for (Iterator<Customer> iter = getIteratorCustomer(); iter.hasNext();) {
				oldCustomer = (Customer) iter.next();
				iter.remove();
				oldCustomer.setStore((Store) null);
			}
		}
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
			newInventory.setStore(this);
		}
	}

	public void removeInventory(Inventory oldInventory) {
		if (oldInventory == null)
			return;
		if (this.inventory != null)
			if (this.inventory.contains(oldInventory)) {
				this.inventory.remove(oldInventory);
				oldInventory.setStore((Store) null);
			}
	}

	public void removeAllInventory() {
		if (inventory != null) {
			Inventory oldInventory;
			for (Iterator<Inventory> iter = getIteratorInventory(); iter.hasNext();) {
				oldInventory = (Inventory) iter.next();
				iter.remove();
				oldInventory.setStore((Store) null);
			}
		}
	}

	public Collection<Staff> getStaff() {
		if (staff == null)
			staff = new HashSet<Staff>();
		return staff;
	}

	public Iterator<Staff> getIteratorStaff() {
		if (staff == null)
			staff = new HashSet<Staff>();
		return staff.iterator();
	}

	public void setStaff(java.util.Collection<Staff> newStaff) {
		removeAllStaff();
		for (Iterator<Staff> iter = newStaff.iterator(); iter.hasNext();)
			addStaff((Staff) iter.next());
	}

	public void addStaff(Staff newStaff) {
		if (newStaff == null)
			return;
		if (this.staff == null)
			this.staff = new HashSet<Staff>();
		if (!this.staff.contains(newStaff)) {
			this.staff.add(newStaff);
			newStaff.setStore(this);
		}
	}

	public void removeStaff(Staff oldStaff) {
		if (oldStaff == null)
			return;
		if (this.staff != null)
			if (this.staff.contains(oldStaff)) {
				this.staff.remove(oldStaff);
				oldStaff.setStore((Store) null);
			}
	}

	public void removeAllStaff() {
		if (staff != null) {
			Staff oldStaff;
			for (Iterator<Staff> iter = getIteratorStaff(); iter.hasNext();) {
				oldStaff = (Staff) iter.next();
				iter.remove();
				oldStaff.setStore((Store) null);
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
				oldAddress.removeStore(this);
			}
			if (newAddress != null) {
				this.address = newAddress;
				this.address.addStore(this);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Store o1 = (Store) o;
		return storeId == o1.storeId && managerStaffId == o1.managerStaffId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(storeId, managerStaffId);
	}

	@Override
	public String toString() {
		return "Store{" + "storeId=" + storeId + ", managerStaffId=" + managerStaffId + ", lastUpdate=" + lastUpdate
				+ '}';
	}
}