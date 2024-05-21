/***********************************************************************
 * Module:  FilmCategory.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class FilmCategory
 ***********************************************************************/

import java.util.*;

/** @pdOid 1906f973-29c9-4770-a639-d3cd47a25b71 */
public class FilmCategory {
   /** @pdOid 93ea5093-39ae-486d-8b38-2192e5f727dd */
   public java.util.Date lastUpdate;
   
   public Category fk_film_category_category;
   public Film fk_film_category_film;
   
   /** @pdOid dbc3e85e-6a8f-497a-810a-468a5f3aab92 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 0056d1ce-2e35-43a8-8f1a-7fa131b2bd62 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }

}