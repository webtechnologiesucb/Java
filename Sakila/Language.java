/***********************************************************************
 * Module:  Language.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Language
 ***********************************************************************/

import java.util.*;

/** @pdOid acdd2338-e103-4706-86cf-a6ffc4012c76 */
public class Language {
   /** @pdOid 9d956f31-4eb4-4601-b523-557b01716572 */
   public long languageId;
   /** @pdOid 45752d3b-6d29-4170-a544-040262be6f7c */
   public java.lang.String name;
   /** @pdOid 6fff7773-8c9c-4bb5-a558-59df1ef488e9 */
   public java.util.Date lastUpdate;
   
   /** @pdRoleInfo migr=no name=Film assc=fkFilmLanguage coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Film> film;
   
   /** @pdOid baf3e09e-fc9d-49ed-bbe0-4df938e323c8 */
   public long getLanguageId() {
      return languageId;
   }
   
   /** @param newLanguageId
    * @pdOid d194a82a-db05-4920-a12d-05879026658b */
   public void setLanguageId(long newLanguageId) {
      languageId = newLanguageId;
   }
   
   /** @pdOid 0b54fd2b-2654-497c-b02d-fc16d525bd70 */
   public java.lang.String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid 14077eda-6850-4121-83c1-b043ff05e61f */
   public void setName(java.lang.String newName) {
      name = newName;
   }
   
   /** @pdOid 30be2641-4c5a-46fd-9591-ba87c835b6b5 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid a5f5812e-dd63-497d-8f72-e005b877bc60 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Film> getFilm() {
      if (film == null)
         film = new java.util.HashSet<Film>();
      return film;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorFilm() {
      if (film == null)
         film = new java.util.HashSet<Film>();
      return film.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newFilm */
   public void setFilm(java.util.Collection<Film> newFilm) {
      removeAllFilm();
      for (java.util.Iterator iter = newFilm.iterator(); iter.hasNext();)
         addFilm((Film)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFilm */
   public void addFilm(Film newFilm) {
      if (newFilm == null)
         return;
      if (this.film == null)
         this.film = new java.util.HashSet<Film>();
      if (!this.film.contains(newFilm))
      {
         this.film.add(newFilm);
         newFilm.setLanguage(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldFilm */
   public void removeFilm(Film oldFilm) {
      if (oldFilm == null)
         return;
      if (this.film != null)
         if (this.film.contains(oldFilm))
         {
            this.film.remove(oldFilm);
            oldFilm.setLanguage((Language)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFilm() {
      if (film != null)
      {
         Film oldFilm;
         for (java.util.Iterator iter = getIteratorFilm(); iter.hasNext();)
         {
            oldFilm = (Film)iter.next();
            iter.remove();
            oldFilm.setLanguage((Language)null);
         }
      }
   }

}