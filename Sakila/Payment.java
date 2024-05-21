/***********************************************************************
 * Module:  Payment.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Payment
 ***********************************************************************/

import java.util.*;

/** @pdOid c700c214-6b84-4e9b-a412-abda7d6df8fc */
public class Payment {
   /** @pdOid 5e65fcaa-2853-438b-92c5-0f0853ab274f */
   public long paymentId;
   /** @pdOid af10c18d-bf4e-4d21-90f6-54df72785942 */
   public double amount;
   /** @pdOid d3fb4ed0-185b-43c0-81fd-1cea79a9c7e8 */
   public java.util.Date paymentDate;
   /** @pdOid 7f90089b-fbf5-47c9-91fd-2c4a038eb1d8 */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Customer assc=fkPaymentCustomer mult=1..1 side=A */
   public Customer customer;
   /** @pdRoleInfo migr=no name=Rental assc=fkPaymentRental mult=0..1 side=A */
   public Rental rental;
   /** @pdRoleInfo migr=no name=Staff assc=fkPaymentStaff mult=1..1 side=A */
   public Staff staff;
   
   /** @pdOid 1b257859-c547-450b-8b30-2405213beef7 */
   public long getPaymentId() {
      return paymentId;
   }
   
   /** @param newPaymentId
    * @pdOid de66180a-6f25-49a0-a540-c3512667f5f7 */
   public void setPaymentId(long newPaymentId) {
      paymentId = newPaymentId;
   }
   
   /** @pdOid a0bcafb8-af44-4c8c-abec-759ecb53c229 */
   public double getAmount() {
      return amount;
   }
   
   /** @param newAmount
    * @pdOid 64a96618-5112-4154-9b39-103da16174b6 */
   public void setAmount(double newAmount) {
      amount = newAmount;
   }
   
   /** @pdOid bdf9a47b-b9b9-46f1-9c0b-b258066acd39 */
   public java.util.Date getPaymentDate() {
      return paymentDate;
   }
   
   /** @param newPaymentDate
    * @pdOid 2ae0b6d6-a80b-473d-aae6-ce4a6c530793 */
   public void setPaymentDate(java.util.Date newPaymentDate) {
      paymentDate = newPaymentDate;
   }
   
   /** @pdOid e3c33506-8234-46af-b8bb-2448ba9b3b41 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 120adb4f-1423-4295-bb15-9405d9d3edde */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
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
            oldCustomer.removePayment(this);
         }
         if (newCustomer != null)
         {
            this.customer = newCustomer;
            this.customer.addPayment(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Rental getRental() {
      return rental;
   }
   
   /** @pdGenerated default parent setter
     * @param newRental */
   public void setRental(Rental newRental) {
      if (this.rental == null || !this.rental.equals(newRental))
      {
         if (this.rental != null)
         {
            Rental oldRental = this.rental;
            this.rental = null;
            oldRental.removePayment(this);
         }
         if (newRental != null)
         {
            this.rental = newRental;
            this.rental.addPayment(this);
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
            oldStaff.removePayment(this);
         }
         if (newStaff != null)
         {
            this.staff = newStaff;
            this.staff.addPayment(this);
         }
      }
   }

}