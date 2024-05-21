/***********************************************************************
 * Module:  Address.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Address
 ***********************************************************************/

import java.util.*;

/** @pdOid 0825b82b-8687-4b27-bad4-60d634accd94 */
public class Address {
   /** @pdOid 0967940d-c8e1-469d-834d-3e5570da82c7 */
   public long addressId;
   /** @pdOid 7f1cfd18-0782-4a69-969f-fbc666a8651c */
   public java.lang.String address;
   /** @pdOid d15d906b-d69e-481f-99da-a6cf48112dd3 */
   public java.lang.String address2;
   /** @pdOid 1ed221db-ebca-4d7c-8f17-29bb58a2f5a0 */
   public java.lang.String district;
   /** @pdOid cff374b4-6206-4d03-b1a0-d578736e6a67 */
   public java.lang.String postalCode;
   /** @pdOid 68628d3b-ad7b-4618-8107-8349d57cb35f */
   public java.lang.String phone;
   /** @pdOid 8ad0ae31-8450-4c0a-9918-e4b2f035c0f2 */
   public geometry location;
   /** @pdOid cd613ed1-12bb-4e86-835a-82b100f0e31c */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Customer assc=fkCustomerAddress coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Customer> customer;
   /** @pdRoleInfo migr=no name=Staff assc=fkStaffAddress coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Staff> staff;
   /** @pdRoleInfo migr=no name=Store assc=fkStoreAddress coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Store> store;
   /** @pdRoleInfo migr=no name=City assc=fkAddressCity mult=1..1 side=A */
   public City city;
   
   /** @pdOid e819f672-0c8b-4c2a-a893-8d2227d31957 */
   public long getAddressId() {
      return addressId;
   }
   
   /** @param newAddressId
    * @pdOid d5ab4261-42a7-40c6-b0a4-35eb89073543 */
   public void setAddressId(long newAddressId) {
      addressId = newAddressId;
   }
   
   /** @pdOid c87809ce-2279-4e44-9e3d-b19e7461bc67 */
   public java.lang.String getAddress() {
      return address;
   }
   
   /** @param newAddress
    * @pdOid c1e659de-2c4f-45fa-b468-482289a08e08 */
   public void setAddress(java.lang.String newAddress) {
      address = newAddress;
   }
   
   /** @pdOid ffb7580b-cf4a-4074-8570-61d0b251cc0b */
   public java.lang.String getAddress2() {
      return address2;
   }
   
   /** @param newAddress2
    * @pdOid dcbda728-94b9-4bd6-9c9e-4ab68165a960 */
   public void setAddress2(java.lang.String newAddress2) {
      address2 = newAddress2;
   }
   
   /** @pdOid b7793a6b-3be3-4b52-b1ff-6623f4cc90de */
   public java.lang.String getDistrict() {
      return district;
   }
   
   /** @param newDistrict
    * @pdOid ee1e80ff-f7ac-4578-93fa-174ca4c082c3 */
   public void setDistrict(java.lang.String newDistrict) {
      district = newDistrict;
   }
   
   /** @pdOid 8c8996b6-d418-44fd-a2c9-e8c18dbeb6e2 */
   public java.lang.String getPostalCode() {
      return postalCode;
   }
   
   /** @param newPostalCode
    * @pdOid d295e65c-a973-4ef6-af93-33be648da592 */
   public void setPostalCode(java.lang.String newPostalCode) {
      postalCode = newPostalCode;
   }
   
   /** @pdOid a62873e1-2c08-45bd-8a18-e868a887ed60 */
   public java.lang.String getPhone() {
      return phone;
   }
   
   /** @param newPhone
    * @pdOid 67829137-5fa4-4eb9-b57b-4f6bce7fc2ae */
   public void setPhone(java.lang.String newPhone) {
      phone = newPhone;
   }
   
   /** @pdOid 0e5b719f-27d4-4e0b-9a92-5b44d9632f3f */
   public geometry getLocation() {
      return location;
   }
   
   /** @param newLocation
    * @pdOid 6a9ecb1c-d118-4da7-90bd-8c9ac2386693 */
   public void setLocation(geometry newLocation) {
      location = newLocation;
   }
   
   /** @pdOid b2b9408e-cea8-4168-a906-6e70e7452ca2 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid f49ef722-8943-45fe-bcdc-f20f9df8fbbd */
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
         newCustomer.setAddress(this);      
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
            oldCustomer.setAddress((Address)null);
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
            oldCustomer.setAddress((Address)null);
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
         newStaff.setAddress(this);      
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
            oldStaff.setAddress((Address)null);
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
            oldStaff.setAddress((Address)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Store> getStore() {
      if (store == null)
         store = new java.util.HashSet<Store>();
      return store;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorStore() {
      if (store == null)
         store = new java.util.HashSet<Store>();
      return store.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newStore */
   public void setStore(java.util.Collection<Store> newStore) {
      removeAllStore();
      for (java.util.Iterator iter = newStore.iterator(); iter.hasNext();)
         addStore((Store)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newStore */
   public void addStore(Store newStore) {
      if (newStore == null)
         return;
      if (this.store == null)
         this.store = new java.util.HashSet<Store>();
      if (!this.store.contains(newStore))
      {
         this.store.add(newStore);
         newStore.setAddress(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldStore */
   public void removeStore(Store oldStore) {
      if (oldStore == null)
         return;
      if (this.store != null)
         if (this.store.contains(oldStore))
         {
            this.store.remove(oldStore);
            oldStore.setAddress((Address)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllStore() {
      if (store != null)
      {
         Store oldStore;
         for (java.util.Iterator iter = getIteratorStore(); iter.hasNext();)
         {
            oldStore = (Store)iter.next();
            iter.remove();
            oldStore.setAddress((Address)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public City getCity() {
      return city;
   }
   
   /** @pdGenerated default parent setter
     * @param newCity */
   public void setCity(City newCity) {
      if (this.city == null || !this.city.equals(newCity))
      {
         if (this.city != null)
         {
            City oldCity = this.city;
            this.city = null;
            oldCity.removeAddress(this);
         }
         if (newCity != null)
         {
            this.city = newCity;
            this.city.addAddress(this);
         }
      }
   }

}