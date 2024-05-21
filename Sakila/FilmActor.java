/***********************************************************************
 * Module:  FilmActor.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class FilmActor
 ***********************************************************************/

import java.util.*;

/** @pdOid 41cc8ba1-919f-4411-b084-1aa6e71947b9 */
public class FilmActor {
   /** @pdOid 98215c78-a385-43e5-9b78-ea2f3275d539 */
   public java.util.Date lastUpdate;
   
   public Actor fk_film_actor_actor;
   public Film fk_film_actor_film;
   
   /** @pdOid 6f285b52-d720-4264-9c07-42478b2a2849 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 209e9664-7ab4-427e-b2c0-331c73b47297 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }

}