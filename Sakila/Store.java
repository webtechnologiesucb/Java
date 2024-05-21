/***********************************************************************
 * Module:  Store.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Store
 ***********************************************************************/

import java.util.*;

/** @pdOid 3c249505-7603-4ea4-ad05-8cc8bbbf6aba */
public class Store {
   /** @pdOid 188e2561-634a-4c29-8300-34e2c67419b6 */
   public long storeId;
   /** @pdOid 9f955669-45d7-4b3c-a016-7656924f5729 */
   public byte managerStaffId;
   /** @pdOid f1e42a3b-5bf1-4553-b5a8-447c018085cb */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Customer assc=fkCustomerStore coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Customer> customer;
   /** @pdRoleInfo migr=no name=Inventory assc=fkInventoryStore coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Inventory> inventory;
   /** @pdRoleInfo migr=no name=Staff assc=fkStaffStore coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Staff> staff;
   /** @pdRoleInfo migr=no name=Address assc=fkStoreAddress mult=1..1 side=A */
   public Address address;
   
   /** @pdOid 9284b74e-7e05-405e-9681-ed3fecaab888 */
   public long getStoreId() {
      return storeId;
   }
   
   /** @param newStoreId
    * @pdOid 478bfd2d-5e7a-4a80-8970-823e0db0d3fc */
   public void setStoreId(long newStoreId) {
      storeId = newStoreId;
   }
   
   /** @pdOid 73ff59eb-9064-495a-bf57-64de35b5a44d */
   public byte getManagerStaffId() {
      return managerStaffId;
   }
   
   /** @param newManagerStaffId
    * @pdOid 9dea007c-f03c-4693-a843-d4f199ac2595 */
   public void setManagerStaffId(byte newManagerStaffId) {
      managerStaffId = newManagerStaffId;
   }
   
   /** @pdOid 205959b1-138c-45b2-912d-61cac7b764a6 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid a115c2a9-bc39-4416-a2b1-91e02dbe6730 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Customer> getCustomer() {
      if (customer == null)
         customer = new java.util.HashSet<Customer>();
      return customer;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorCustomer() {
      if (customer == null)
         customer = new java.util.HashSet<Customer>();
      return customer.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newCustomer */
   public void setCustomer(java.util.Collection<Customer> newCustomer) {
      removeAllCustomer();
      for (java.util.Iterator iter = newCustomer.iterator(); iter.hasNext();)
         addCustomer((Customer)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newCustomer */
   public void addCustomer(Customer newCustomer) {
      if (newCustomer == null)
         return;
      if (this.customer == null)
         this.customer = new java.util.HashSet<Customer>();
      if (!this.customer.contains(newCustomer))
      {
         this.customer.add(newCustomer);
         newCustomer.setStore(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldCustomer */
   public void removeCustomer(Customer oldCustomer) {
      if (oldCustomer == null)
         return;
      if (this.customer != null)
         if (this.customer.contains(oldCustomer))
         {
            this.customer.remove(oldCustomer);
            oldCustomer.setStore((Store)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllCustomer() {
      if (customer != null)
      {
         Customer oldCustomer;
         for (java.util.Iterator iter = getIteratorCustomer(); iter.hasNext();)
         {
            oldCustomer = (Customer)iter.next();
            iter.remove();
            oldCustomer.setStore((Store)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Inventory> getInventory() {
      if (inventory == null)
         inventory = new java.util.HashSet<Inventory>();
      return inventory;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorInventory() {
      if (inventory == null)
         inventory = new java.util.HashSet<Inventory>();
      return inventory.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newInventory */
   public void setInventory(java.util.Collection<Inventory> newInventory) {
      removeAllInventory();
      for (java.util.Iterator iter = newInventory.iterator(); iter.hasNext();)
         addInventory((Inventory)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newInventory */
   public void addInventory(Inventory newInventory) {
      if (newInventory == null)
         return;
      if (this.inventory == null)
         this.inventory = new java.util.HashSet<Inventory>();
      if (!this.inventory.contains(newInventory))
      {
         this.inventory.add(newInventory);
         newInventory.setStore(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldInventory */
   public void removeInventory(Inventory oldInventory) {
      if (oldInventory == null)
         return;
      if (this.inventory != null)
         if (this.inventory.contains(oldInventory))
         {
            this.inventory.remove(oldInventory);
            oldInventory.setStore((Store)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllInventory() {
      if (inventory != null)
      {
         Inventory oldInventory;
         for (java.util.Iterator iter = getIteratorInventory(); iter.hasNext();)
         {
            oldInventory = (Inventory)iter.next();
            iter.remove();
            oldInventory.setStore((Store)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Staff> getStaff() {
      if (staff == null)
         staff = new java.util.HashSet<Staff>();
      return staff;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorStaff() {
      if (staff == null)
         staff = new java.util.HashSet<Staff>();
      return staff.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newStaff */
   public void setStaff(java.util.Collection<Staff> newStaff) {
      removeAllStaff();
      for (java.util.Iterator iter = newStaff.iterator(); iter.hasNext();)
         addStaff((Staff)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newStaff */
   public void addStaff(Staff newStaff) {
      if (newStaff == null)
         return;
      if (this.staff == null)
         this.staff = new java.util.HashSet<Staff>();
      if (!this.staff.contains(newStaff))
      {
         this.staff.add(newStaff);
         newStaff.setStore(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldStaff */
   public void removeStaff(Staff oldStaff) {
      if (oldStaff == null)
         return;
      if (this.staff != null)
         if (this.staff.contains(oldStaff))
         {
            this.staff.remove(oldStaff);
            oldStaff.setStore((Store)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllStaff() {
      if (staff != null)
      {
         Staff oldStaff;
         for (java.util.Iterator iter = getIteratorStaff(); iter.hasNext();)
         {
            oldStaff = (Staff)iter.next();
            iter.remove();
            oldStaff.setStore((Store)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Address getAddress() {
      return address;
   }
   
   /** @pdGenerated default parent setter
     * @param newAddress */
   public void setAddress(Address newAddress) {
      if (this.address == null || !this.address.equals(newAddress))
      {
         if (this.address != null)
         {
            Address oldAddress = this.address;
            this.address = null;
            oldAddress.removeStore(this);
         }
         if (newAddress != null)
         {
            this.address = newAddress;
            this.address.addStore(this);
         }
      }
   }

}