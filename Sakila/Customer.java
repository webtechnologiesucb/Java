/***********************************************************************
 * Module:  Customer.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Customer
 ***********************************************************************/

import java.util.*;

/** @pdOid f9972631-1cb8-45f2-88c8-1dd1168948ae */
public class Customer {
   /** @pdOid e81b3de6-84f4-469e-b1df-53879297c469 */
   public long customerId;
   /** @pdOid 26dc2541-bfa6-4892-8bb5-f414ab03e32e */
   public java.lang.String firstName;
   /** @pdOid cfb26c56-1c7c-4fb8-9c58-813fb18f74bc */
   public java.lang.String lastName;
   /** @pdOid 437e97ea-e3a9-44a3-9952-5864d9f90839 */
   public java.lang.String email;
   /** @pdOid cc1f61f8-5d9c-4c99-8a2f-272b0c651f43 */
   public long active = 1;
   /** @pdOid 5946bb90-12d6-438e-9311-d77cec8fd3fe */
   public java.util.Date createDate;
   /** @pdOid 5aed9234-cecd-4bf0-b23f-15c737e711cb */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Payment assc=fkPaymentCustomer coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Payment> payment;
   /** @pdRoleInfo migr=no name=Rental assc=fkRentalCustomer coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Rental> rental;
   /** @pdRoleInfo migr=no name=Address assc=fkCustomerAddress mult=1..1 side=A */
   public Address address;
   /** @pdRoleInfo migr=no name=Store assc=fkCustomerStore mult=1..1 side=A */
   public Store store;
   
   /** @pdOid d594c68e-fafa-4590-a23e-5988f42af47c */
   public long getCustomerId() {
      return customerId;
   }
   
   /** @param newCustomerId
    * @pdOid 4fd9c26d-d6ba-4e91-9198-aa478fe32f45 */
   public void setCustomerId(long newCustomerId) {
      customerId = newCustomerId;
   }
   
   /** @pdOid ebfc2f2c-ccdf-4861-91e5-b3851cc75f43 */
   public java.lang.String getFirstName() {
      return firstName;
   }
   
   /** @param newFirstName
    * @pdOid 5ca3df63-dc60-4f9c-866f-aab58cbdb9f7 */
   public void setFirstName(java.lang.String newFirstName) {
      firstName = newFirstName;
   }
   
   /** @pdOid a662109b-7ca9-4fe3-884c-c0f5a405e9e6 */
   public java.lang.String getLastName() {
      return lastName;
   }
   
   /** @param newLastName
    * @pdOid dddf50a4-22d0-43bd-a261-d9e467cef865 */
   public void setLastName(java.lang.String newLastName) {
      lastName = newLastName;
   }
   
   /** @pdOid 26b8e944-c4f0-4293-b433-0e0cfbf0c9ed */
   public java.lang.String getEmail() {
      return email;
   }
   
   /** @param newEmail
    * @pdOid fb6a542d-721b-4861-8234-c821f47e3d78 */
   public void setEmail(java.lang.String newEmail) {
      email = newEmail;
   }
   
   /** @pdOid 818fcd9f-9bb5-4ed9-8fb3-e90bb34b6e35 */
   public long getActive() {
      return active;
   }
   
   /** @param newActive
    * @pdOid 46ad1ab5-53d5-46cf-ae72-f3d394b1a1b3 */
   public void setActive(long newActive) {
      active = newActive;
   }
   
   /** @pdOid 74ff6d10-86e2-4af1-901e-27f5c388f2cb */
   public java.util.Date getCreateDate() {
      return createDate;
   }
   
   /** @param newCreateDate
    * @pdOid 0f48b666-9750-4bfd-9bfb-be2b376cbab2 */
   public void setCreateDate(java.util.Date newCreateDate) {
      createDate = newCreateDate;
   }
   
   /** @pdOid ad0dd95a-ea5a-42cc-8519-83fe66ff9b18 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 43d6dcf2-31f7-4185-93a8-4426b9e89f83 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Payment> getPayment() {
      if (payment == null)
         payment = new java.util.HashSet<Payment>();
      return payment;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPayment() {
      if (payment == null)
         payment = new java.util.HashSet<Payment>();
      return payment.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPayment */
   public void setPayment(java.util.Collection<Payment> newPayment) {
      removeAllPayment();
      for (java.util.Iterator iter = newPayment.iterator(); iter.hasNext();)
         addPayment((Payment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPayment */
   public void addPayment(Payment newPayment) {
      if (newPayment == null)
         return;
      if (this.payment == null)
         this.payment = new java.util.HashSet<Payment>();
      if (!this.payment.contains(newPayment))
      {
         this.payment.add(newPayment);
         newPayment.setCustomer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldPayment */
   public void removePayment(Payment oldPayment) {
      if (oldPayment == null)
         return;
      if (this.payment != null)
         if (this.payment.contains(oldPayment))
         {
            this.payment.remove(oldPayment);
            oldPayment.setCustomer((Customer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPayment() {
      if (payment != null)
      {
         Payment oldPayment;
         for (java.util.Iterator iter = getIteratorPayment(); iter.hasNext();)
         {
            oldPayment = (Payment)iter.next();
            iter.remove();
            oldPayment.setCustomer((Customer)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Rental> getRental() {
      if (rental == null)
         rental = new java.util.HashSet<Rental>();
      return rental;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRental() {
      if (rental == null)
         rental = new java.util.HashSet<Rental>();
      return rental.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRental */
   public void setRental(java.util.Collection<Rental> newRental) {
      removeAllRental();
      for (java.util.Iterator iter = newRental.iterator(); iter.hasNext();)
         addRental((Rental)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRental */
   public void addRental(Rental newRental) {
      if (newRental == null)
         return;
      if (this.rental == null)
         this.rental = new java.util.HashSet<Rental>();
      if (!this.rental.contains(newRental))
      {
         this.rental.add(newRental);
         newRental.setCustomer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRental */
   public void removeRental(Rental oldRental) {
      if (oldRental == null)
         return;
      if (this.rental != null)
         if (this.rental.contains(oldRental))
         {
            this.rental.remove(oldRental);
            oldRental.setCustomer((Customer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRental() {
      if (rental != null)
      {
         Rental oldRental;
         for (java.util.Iterator iter = getIteratorRental(); iter.hasNext();)
         {
            oldRental = (Rental)iter.next();
            iter.remove();
            oldRental.setCustomer((Customer)null);
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
            oldAddress.removeCustomer(this);
         }
         if (newAddress != null)
         {
            this.address = newAddress;
            this.address.addCustomer(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Store getStore() {
      return store;
   }
   
   /** @pdGenerated default parent setter
     * @param newStore */
   public void setStore(Store newStore) {
      if (this.store == null || !this.store.equals(newStore))
      {
         if (this.store != null)
         {
            Store oldStore = this.store;
            this.store = null;
            oldStore.removeCustomer(this);
         }
         if (newStore != null)
         {
            this.store = newStore;
            this.store.addCustomer(this);
         }
      }
   }

}