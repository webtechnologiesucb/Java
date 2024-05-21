/***********************************************************************
 * Module:  Country.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Country
 ***********************************************************************/

import java.util.*;

/** @pdOid 6aa6262f-b3fd-4630-98f9-9f6e9279431d */
public class Country {
   /** @pdOid 9dcbdd8b-1d17-4fef-8e2b-9e11258db05f */
   public long countryId;
   /** @pdOid 9766ffe7-82d9-40ba-9ba7-46e27dec5d15 */
   public java.lang.String country;
   /** @pdOid 211d2b6a-f505-4c44-a0fd-89edaf5a9bf1 */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=City assc=fkCityCountry coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<City> city;
   
   /** @pdOid 2c7d7813-4a58-481a-a46a-34c13f666fea */
   public long getCountryId() {
      return countryId;
   }
   
   /** @param newCountryId
    * @pdOid f635498f-1eb8-4d64-98a5-acf96ae0d217 */
   public void setCountryId(long newCountryId) {
      countryId = newCountryId;
   }
   
   /** @pdOid b2192a5d-1d10-4ab4-b412-e7a8cdd8d0a0 */
   public java.lang.String getCountry() {
      return country;
   }
   
   /** @param newCountry
    * @pdOid 3b3c9127-22c3-4d28-be4c-94f3212ad97a */
   public void setCountry(java.lang.String newCountry) {
      country = newCountry;
   }
   
   /** @pdOid b4fd09bd-479d-41c7-8883-1de9c98f432a */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid a6a13bbf-6085-48d7-bd03-eeb256254e50 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<City> getCity() {
      if (city == null)
         city = new java.util.HashSet<City>();
      return city;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorCity() {
      if (city == null)
         city = new java.util.HashSet<City>();
      return city.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newCity */
   public void setCity(java.util.Collection<City> newCity) {
      removeAllCity();
      for (java.util.Iterator iter = newCity.iterator(); iter.hasNext();)
         addCity((City)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newCity */
   public void addCity(City newCity) {
      if (newCity == null)
         return;
      if (this.city == null)
         this.city = new java.util.HashSet<City>();
      if (!this.city.contains(newCity))
      {
         this.city.add(newCity);
         newCity.setCountry(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldCity */
   public void removeCity(City oldCity) {
      if (oldCity == null)
         return;
      if (this.city != null)
         if (this.city.contains(oldCity))
         {
            this.city.remove(oldCity);
            oldCity.setCountry((Country)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllCity() {
      if (city != null)
      {
         City oldCity;
         for (java.util.Iterator iter = getIteratorCity(); iter.hasNext();)
         {
            oldCity = (City)iter.next();
            iter.remove();
            oldCity.setCountry((Country)null);
         }
      }
   }

}