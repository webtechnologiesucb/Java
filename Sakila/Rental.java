/***********************************************************************
 * Module:  Rental.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Rental
 ***********************************************************************/

import java.util.*;

/** @pdOid a5b0f1d4-6f47-4156-9939-3db9d642d957 */
public class Rental {
   /** @pdOid 7ff6e1bb-4d5d-41dc-97a6-ca8436b56de5 */
   public long rentalId;
   /** @pdOid 6b918168-f9f9-4a45-8432-1dd41eb92eb6 */
   public java.util.Date rentalDate;
   /** @pdOid 0bf36207-5f38-42a4-a626-56616722b1e1 */
   public int inventoryId;
   /** @pdOid 544fc935-37e3-4b4d-bb1d-705510e0bb66 */
   public short customerId;
   /** @pdOid f143ef95-2e99-40c1-a7d8-94c24fecfe2a */
   public java.util.Date returnDate;
   /** @pdOid eb52c22e-8b5e-4235-a635-b0ff133dfc67 */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Payment assc=fkPaymentRental coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Payment> payment;
   /** @pdRoleInfo migr=no name=Customer assc=fkRentalCustomer mult=1..1 side=A */
   public Customer customer;
   /** @pdRoleInfo migr=no name=Inventory assc=fkRentalInventory mult=1..1 side=A */
   public Inventory inventory;
   /** @pdRoleInfo migr=no name=Staff assc=fkRentalStaff mult=1..1 side=A */
   public Staff staff;
   
   /** @pdOid b9b31235-6c64-4ec9-9225-783df23016a1 */
   public long getRentalId() {
      return rentalId;
   }
   
   /** @param newRentalId
    * @pdOid c97641a9-4889-4a9a-918e-92b16d1f03ed */
   public void setRentalId(long newRentalId) {
      rentalId = newRentalId;
   }
   
   /** @pdOid 84fe9ab2-a2d1-4eb0-af0c-47fa003edd7a */
   public java.util.Date getRentalDate() {
      return rentalDate;
   }
   
   /** @param newRentalDate
    * @pdOid 644c1800-3844-470c-8be6-dac9e96c01f1 */
   public void setRentalDate(java.util.Date newRentalDate) {
      rentalDate = newRentalDate;
   }
   
   /** @pdOid 413ee07f-0acd-4038-a5c0-caf9cebaf629 */
   public int getInventoryId() {
      return inventoryId;
   }
   
   /** @param newInventoryId
    * @pdOid 635e5737-646e-42f3-ad3c-c616dd9fc284 */
   public void setInventoryId(int newInventoryId) {
      inventoryId = newInventoryId;
   }
   
   /** @pdOid 91bdb1be-02db-4d4c-8a66-0ab22216cb9e */
   public short getCustomerId() {
      return customerId;
   }
   
   /** @param newCustomerId
    * @pdOid c6b0fb50-2f34-40e1-b823-9c9b943be437 */
   public void setCustomerId(short newCustomerId) {
      customerId = newCustomerId;
   }
   
   /** @pdOid 28fa985d-ff87-451f-8bd4-88142d2bcda2 */
   public java.util.Date getReturnDate() {
      return returnDate;
   }
   
   /** @param newReturnDate
    * @pdOid 016c1948-bfda-472c-8054-cd549320bab8 */
   public void setReturnDate(java.util.Date newReturnDate) {
      returnDate = newReturnDate;
   }
   
   /** @pdOid 231817b8-ea41-4dc5-8c8f-74c801e13c02 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid df311db3-1e11-454b-b12a-458fd4091e16 */
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
         newPayment.setRental(this);      
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
            oldPayment.setRental((Rental)null);
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
            oldPayment.setRental((Rental)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Customer getCustomer() {
      return customer;
   }
   
   /** @pdGenerated default parent setter
     * @param newCustomer */
   public void setCustomer(Customer newCustomer) {
      if (this.customer == null || !this.customer.equals(newCustomer))
      {
         if (this.customer != null)
         {
            Customer oldCustomer = this.customer;
            this.customer = null;
            oldCustomer.removeRental(this);
         }
         if (newCustomer != null)
         {
            this.customer = newCustomer;
            this.customer.addRental(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Inventory getInventory() {
      return inventory;
   }
   
   /** @pdGenerated default parent setter
     * @param newInventory */
   public void setInventory(Inventory newInventory) {
      if (this.inventory == null || !this.inventory.equals(newInventory))
      {
         if (this.inventory != null)
         {
            Inventory oldInventory = this.inventory;
            this.inventory = null;
            oldInventory.removeRental(this);
         }
         if (newInventory != null)
         {
            this.inventory = newInventory;
            this.inventory.addRental(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Staff getStaff() {
      return staff;
   }
   
   /** @pdGenerated default parent setter
     * @param newStaff */
   public void setStaff(Staff newStaff) {
      if (this.staff == null || !this.staff.equals(newStaff))
      {
         if (this.staff != null)
         {
            Staff oldStaff = this.staff;
            this.staff = null;
            oldStaff.removeRental(this);
         }
         if (newStaff != null)
         {
            this.staff = newStaff;
            this.staff.addRental(this);
         }
      }
   }

}