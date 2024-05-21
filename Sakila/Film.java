/***********************************************************************
 * Module:  Film.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Film
 ***********************************************************************/

import java.util.*;

/** @pdOid 1481565b-3564-4aca-ac6c-91b9883a7552 */
public class Film {
   /** @pdOid b2a54a78-f279-4fe6-ade0-628f67d4f06e */
   public long filmId;
   /** @pdOid 5bc296b0-c213-4ebe-a089-f4df0a12afb1 */
   public java.lang.String title;
   /** @pdOid f91b1da7-c837-4336-998f-674d1230d6fa */
   public java.lang.String description;
   /** @pdOid 2c71db1c-411d-4be4-9f57-75186768ae52 */
   public year releaseYear;
   /** @pdOid 525ea5ae-bd8c-4cf5-b555-b95d460272eb */
   public byte rentalDuration = 3;
   /** @pdOid fbfc6f34-bf76-4cc1-8cee-c212fc6e4165 */
   public double rentalRate = 4.99;
   /** @pdOid f2c27b2c-ddb3-4bbb-a0d1-f530b037c7d0 */
   public short length;
   /** @pdOid 18c700bc-037a-49c0-b198-d110c51569f6 */
   public double replacementCost = 19.99;
   /** @pdOid 66f64c89-3764-40d5-a5de-aa6bb3bd26de */
   public enum('g','pg','pg-13','r','nc-17') rating;
   /** @pdOid 98f35431-f128-488c-8b6e-b1b62a259209 */
   public set('trailers','commentaries','deleted scenes','behind the scenes') specialFeatures;
   /** @pdOid 06f6d73b-4b44-4822-852d-bbadae681201 */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Inventory assc=fkInventoryFilm coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Inventory> inventory;
   public java.util.Collection filmActor;
   public java.util.Collection filmCategory;
   /** @pdRoleInfo migr=no name=Language assc=fkFilmLanguage mult=1..1 side=A */
   public Language language;
   
   /** @pdOid db4d981c-24bf-4fd0-a735-89309ea509fc */
   public long getFilmId() {
      return filmId;
   }
   
   /** @param newFilmId
    * @pdOid 928d5780-3c7f-4b4f-9f13-cdfad28726c3 */
   public void setFilmId(long newFilmId) {
      filmId = newFilmId;
   }
   
   /** @pdOid 90163a3a-4230-4816-a281-65f4a3719dc0 */
   public java.lang.String getTitle() {
      return title;
   }
   
   /** @param newTitle
    * @pdOid 55be9683-e4a3-4fd3-b1cf-f1814eab8cee */
   public void setTitle(java.lang.String newTitle) {
      title = newTitle;
   }
   
   /** @pdOid 96d52cb2-6c9c-4405-ad4c-45c947759c26 */
   public java.lang.String getDescription() {
      return description;
   }
   
   /** @param newDescription
    * @pdOid 19ff8da4-eade-4748-878d-270106fdca45 */
   public void setDescription(java.lang.String newDescription) {
      description = newDescription;
   }
   
   /** @pdOid 3b948972-e9fe-42fc-b036-0aebbf0e2641 */
   public year getReleaseYear() {
      return releaseYear;
   }
   
   /** @param newReleaseYear
    * @pdOid 74a36fc7-bf84-42e3-852f-f8c2899bf729 */
   public void setReleaseYear(year newReleaseYear) {
      releaseYear = newReleaseYear;
   }
   
   /** @pdOid 3ea7238d-bbe0-49b1-b9a3-2d09f53e1d55 */
   public byte getRentalDuration() {
      return rentalDuration;
   }
   
   /** @param newRentalDuration
    * @pdOid bbad5361-9259-44b3-bba1-fe5c617e084e */
   public void setRentalDuration(byte newRentalDuration) {
      rentalDuration = newRentalDuration;
   }
   
   /** @pdOid e3c2d69c-a12d-4c95-a296-322c814df6e1 */
   public double getRentalRate() {
      return rentalRate;
   }
   
   /** @param newRentalRate
    * @pdOid a26b12a4-19c1-4d20-9fac-9967bc048047 */
   public void setRentalRate(double newRentalRate) {
      rentalRate = newRentalRate;
   }
   
   /** @pdOid 4a7742f5-b4ea-4978-8040-7c3caf6ca174 */
   public short getLength() {
      return length;
   }
   
   /** @param newLength
    * @pdOid 520d72d3-b3b7-43e6-b49d-2fd0bbc4b3bd */
   public void setLength(short newLength) {
      length = newLength;
   }
   
   /** @pdOid b239f5ab-e45a-41cf-8e9a-31c3d220b205 */
   public double getReplacementCost() {
      return replacementCost;
   }
   
   /** @param newReplacementCost
    * @pdOid 7984fd68-9614-4ca2-8fd2-a7bc1fb5f181 */
   public void setReplacementCost(double newReplacementCost) {
      replacementCost = newReplacementCost;
   }
   
   /** @pdOid 93329ddc-9b62-4835-8faf-061c821eed42 */
   public enum('g','pg','pg-13','r','nc-17') getRating() {
      return rating;
   }
   
   /** @param newRating
    * @pdOid e5f7147a-d4eb-4ad9-80cc-8516006bdd97 */
   public void setRating(enum('g','pg','pg-13','r','nc-17') newRating) {
      rating = newRating;
   }
   
   /** @pdOid cbcc3835-3221-49e0-b6a7-36a7590ab0fd */
   public set('trailers','commentaries','deleted scenes','behind the scenes') getSpecialFeatures() {
      return specialFeatures;
   }
   
   /** @param newSpecialFeatures
    * @pdOid a3be2362-7a80-499c-8a25-9afc2c371673 */
   public void setSpecialFeatures(set('trailers','commentaries','deleted scenes','behind the scenes') newSpecialFeatures) {
      specialFeatures = newSpecialFeatures;
   }
   
   /** @pdOid 5940d9f9-0b31-41ce-8b60-8c907a3e3c52 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 2b2a64cd-3c9e-4367-a978-dc8896410edc */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
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
         newInventory.setFilm(this);      
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
            oldInventory.setFilm((Film)null);
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
            oldInventory.setFilm((Film)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Language getLanguage() {
      return language;
   }
   
   /** @pdGenerated default parent setter
     * @param newLanguage */
   public void setLanguage(Language newLanguage) {
      if (this.language == null || !this.language.equals(newLanguage))
      {
         if (this.language != null)
         {
            Language oldLanguage = this.language;
            this.language = null;
            oldLanguage.removeFilm(this);
         }
         if (newLanguage != null)
         {
            this.language = newLanguage;
            this.language.addFilm(this);
         }
      }
   }

}