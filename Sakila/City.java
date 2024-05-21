/***********************************************************************
 * Module:  City.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class City
 ***********************************************************************/

import java.util.*;

/** @pdOid 2a085e86-4512-42ca-a24b-becabbe9f551 */
public class City {
   /** @pdOid 0531f06b-8ac6-4f46-be67-9aaec016b16d */
   public long cityId;
   /** @pdOid 68db3cba-96fc-4294-8361-818c72eecb4b */
   public java.lang.String city;
   /** @pdOid 2c0b1495-fe29-4c74-8244-97cd3fc4a42e */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Address assc=fkAddressCity coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Address> address;
   /** @pdRoleInfo migr=no name=Country assc=fkCityCountry mult=1..1 side=A */
   public Country country;
   
   /** @pdOid 8e8eea7d-c751-4163-b9fa-f09672a3c033 */
   public long getCityId() {
      return cityId;
   }
   
   /** @param newCityId
    * @pdOid 12641f5a-c8ed-4ef4-a790-e3ed082706b8 */
   public void setCityId(long newCityId) {
      cityId = newCityId;
   }
   
   /** @pdOid 18b4b9ba-2c13-4800-9de8-12ca541c2b4e */
   public java.lang.String getCity() {
      return city;
   }
   
   /** @param newCity
    * @pdOid ec75badd-3917-49fa-80a5-a1c9c659f432 */
   public void setCity(java.lang.String newCity) {
      city = newCity;
   }
   
   /** @pdOid 20c2d89a-6729-4a3e-bcdb-1f714123c7cf */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 4b4f732c-8cf0-48d1-b6a6-41d3b62c1de3 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Address> getAddress() {
      if (address == null)
         address = new java.util.HashSet<Address>();
      return address;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAddress() {
      if (address == null)
         address = new java.util.HashSet<Address>();
      return address.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAddress */
   public void setAddress(java.util.Collection<Address> newAddress) {
      removeAllAddress();
      for (java.util.Iterator iter = newAddress.iterator(); iter.hasNext();)
         addAddress((Address)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAddress */
   public void addAddress(Address newAddress) {
      if (newAddress == null)
         return;
      if (this.address == null)
         this.address = new java.util.HashSet<Address>();
      if (!this.address.contains(newAddress))
      {
         this.address.add(newAddress);
         newAddress.setCity(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldAddress */
   public void removeAddress(Address oldAddress) {
      if (oldAddress == null)
         return;
      if (this.address != null)
         if (this.address.contains(oldAddress))
         {
            this.address.remove(oldAddress);
            oldAddress.setCity((City)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAddress() {
      if (address != null)
      {
         Address oldAddress;
         for (java.util.Iterator iter = getIteratorAddress(); iter.hasNext();)
         {
            oldAddress = (Address)iter.next();
            iter.remove();
            oldAddress.setCity((City)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Country getCountry() {
      return country;
   }
   
   /** @pdGenerated default parent setter
     * @param newCountry */
   public void setCountry(Country newCountry) {
      if (this.country == null || !this.country.equals(newCountry))
      {
         if (this.country != null)
         {
            Country oldCountry = this.country;
            this.country = null;
            oldCountry.removeCity(this);
         }
         if (newCountry != null)
         {
            this.country = newCountry;
            this.country.addCity(this);
         }
      }
   }

}