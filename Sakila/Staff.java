/***********************************************************************
 * Module:  Staff.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Staff
 ***********************************************************************/

import java.util.*;

/** @pdOid ecbb5c83-1c2f-4577-a727-efcafe1450aa */
public class Staff {
   /** @pdOid 36fdcff8-b7b1-4c43-8b56-b4579eae7789 */
   public long staffId;
   /** @pdOid cb75e52e-2a74-471a-bd0f-0456098b8bde */
   public java.lang.String firstName;
   /** @pdOid 158be0cf-a4eb-4241-b9be-4b87a394c857 */
   public java.lang.String lastName;
   /** @pdOid 99c61205-2124-4a56-a224-510afd507764 */
   public java.lang.String picture;
   /** @pdOid dc917e4f-edd9-4de5-ba27-bbfee312936e */
   public java.lang.String email;
   /** @pdOid 36796d58-ed68-4dfc-a705-d9c9aedf1e5a */
   public long active = 1;
   /** @pdOid 6ae2ad5b-9e7a-42f5-82db-ab2bc0f8050d */
   public java.lang.String username;
   /** @pdOid 23e8b48c-bd5e-447c-a930-c80365b1719f */
   public java.lang.String password;
   /** @pdOid b261de1f-edbe-4e66-9ea7-0ff164840b5c */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Payment assc=fkPaymentStaff coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Payment> payment;
   /** @pdRoleInfo migr=no name=Rental assc=fkRentalStaff coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Rental> rental;
   /** @pdRoleInfo migr=no name=Address assc=fkStaffAddress mult=1..1 side=A */
   public Address address;
   /** @pdRoleInfo migr=no name=Store assc=fkStaffStore mult=1..1 side=A */
   public Store store;
   
   /** @pdOid 075dec6f-0b5a-4502-b30e-d3c02aee3d0a */
   public long getStaffId() {
      return staffId;
   }
   
   /** @param newStaffId
    * @pdOid a0637b8f-f598-4473-a293-7943200306b3 */
   public void setStaffId(long newStaffId) {
      staffId = newStaffId;
   }
   
   /** @pdOid 746739a8-9836-4235-a3d5-354c1d2cc977 */
   public java.lang.String getFirstName() {
      return firstName;
   }
   
   /** @param newFirstName
    * @pdOid 09056406-2347-4f16-b071-524fc94e03e8 */
   public void setFirstName(java.lang.String newFirstName) {
      firstName = newFirstName;
   }
   
   /** @pdOid a101959a-3a07-4a0e-a3a0-c019185775f1 */
   public java.lang.String getLastName() {
      return lastName;
   }
   
   /** @param newLastName
    * @pdOid 24d16e0c-38a9-4fcd-bf9e-5d6baafe3eb2 */
   public void setLastName(java.lang.String newLastName) {
      lastName = newLastName;
   }
   
   /** @pdOid 1166778e-9fdb-4744-9353-4bc4ae7997d5 */
   public java.lang.String getPicture() {
      return picture;
   }
   
   /** @param newPicture
    * @pdOid 8a636f57-3321-4b56-bb99-e8b04c5f5884 */
   public void setPicture(java.lang.String newPicture) {
      picture = newPicture;
   }
   
   /** @pdOid 345f84ee-c381-4b7f-83a9-65153bbf3e00 */
   public java.lang.String getEmail() {
      return email;
   }
   
   /** @param newEmail
    * @pdOid 93f7559c-5925-4f90-b6e6-527beed41fc6 */
   public void setEmail(java.lang.String newEmail) {
      email = newEmail;
   }
   
   /** @pdOid 99e427b2-d8b5-4253-8797-62899e3df110 */
   public long getActive() {
      return active;
   }
   
   /** @param newActive
    * @pdOid 5ecbae23-8ae9-4936-9a02-e859bf0425e4 */
   public void setActive(long newActive) {
      active = newActive;
   }
   
   /** @pdOid 492a3154-e65a-469c-825e-60ff9c238bf2 */
   public java.lang.String getUsername() {
      return username;
   }
   
   /** @param newUsername
    * @pdOid 5f1dc340-227e-4761-ad8c-bc8b79a0d170 */
   public void setUsername(java.lang.String newUsername) {
      username = newUsername;
   }
   
   /** @pdOid d67157d9-d3ba-4148-a188-18cbbfaf59bf */
   public java.lang.String getPassword() {
      return password;
   }
   
   /** @param newPassword
    * @pdOid b4e9ab99-751b-4bfd-a1c4-0b44f35390da */
   public void setPassword(java.lang.String newPassword) {
      password = newPassword;
   }
   
   /** @pdOid af309ce7-60e7-48c7-8d4a-795727073f96 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid e8eb60ba-b65d-424e-b04d-cf056b6c8ddb */
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
         newPayment.setStaff(this);      
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
            oldPayment.setStaff((Staff)null);
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
            oldPayment.setStaff((Staff)null);
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
         newRental.setStaff(this);      
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
            oldRental.setStaff((Staff)null);
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
            oldRental.setStaff((Staff)null);
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
            oldAddress.removeStaff(this);
         }
         if (newAddress != null)
         {
            this.address = newAddress;
            this.address.addStaff(this);
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
            oldStore.removeStaff(this);
         }
         if (newStore != null)
         {
            this.store = newStore;
            this.store.addStaff(this);
         }
      }
   }

}