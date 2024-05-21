/***********************************************************************
 * Module:  Inventory.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Inventory
 ***********************************************************************/

import java.util.*;

/** @pdOid 69d73318-b1a8-449a-8123-6c1698a36c64 */
public class Inventory {
   /** @pdOid 241fc73c-34f1-4177-844c-17d78cb43d43 */
   public long inventoryId;
   /** @pdOid 3a2207fa-934b-4c12-a7bc-1ce868f80aa4 */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Rental assc=fkRentalInventory coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Rental> rental;
   /** @pdRoleInfo migr=no name=Film assc=fkInventoryFilm mult=1..1 side=A */
   public Film film;
   /** @pdRoleInfo migr=no name=Store assc=fkInventoryStore mult=1..1 side=A */
   public Store store;
   
   /** @pdOid 81783cc0-2ff8-4b9d-a2d0-2280579d0986 */
   public long getInventoryId() {
      return inventoryId;
   }
   
   /** @param newInventoryId
    * @pdOid 65c111f2-2282-4ac0-9dba-e04054105eb9 */
   public void setInventoryId(long newInventoryId) {
      inventoryId = newInventoryId;
   }
   
   /** @pdOid 38656f78-6a42-4d2f-8d90-24abbea35d75 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 2e71325b-fffd-4546-87b4-25a6920309a3 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
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
         newRental.setInventory(this);      
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
            oldRental.setInventory((Inventory)null);
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
            oldRental.setInventory((Inventory)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Film getFilm() {
      return film;
   }
   
   /** @pdGenerated default parent setter
     * @param newFilm */
   public void setFilm(Film newFilm) {
      if (this.film == null || !this.film.equals(newFilm))
      {
         if (this.film != null)
         {
            Film oldFilm = this.film;
            this.film = null;
            oldFilm.removeInventory(this);
         }
         if (newFilm != null)
         {
            this.film = newFilm;
            this.film.addInventory(this);
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
            oldStore.removeInventory(this);
         }
         if (newStore != null)
         {
            this.store = newStore;
            this.store.addInventory(this);
         }
      }
   }

}