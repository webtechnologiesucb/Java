/***********************************************************************
 * Module:  Actor.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Actor
 ***********************************************************************/

import java.util.*;

/** @pdOid c6eeb972-2454-481f-b443-012402d260ab */
public class Actor {
   /** @pdOid a0085879-4590-419b-93e6-3a2464c876f7 */
   public long actorId;
   /** @pdOid 2ebd44ac-01bf-488f-ad30-c4fb34c14778 */
   public java.lang.String firstName;
   /** @pdOid 39faaa0e-0dd6-4f71-a8e7-03e70f8e34c3 */
   public java.lang.String lastName;
   /** @pdOid c0bf9113-bbb8-4bf0-ae61-9c79d05e1595 */
   public java.util.Date lastUpdate;
   
   public java.util.Collection filmActor;
   
   /** @pdOid d5b6c644-50d1-46c0-914f-7b64df7dd6ef */
   public long getActorId() {
      return actorId;
   }
   
   /** @param newActorId
    * @pdOid 9e9c0a78-9bf9-492d-8954-de686a2b57f2 */
   public void setActorId(long newActorId) {
      actorId = newActorId;
   }
   
   /** @pdOid 81186c13-6edc-416a-b68f-2896827ce1b5 */
   public java.lang.String getFirstName() {
      return firstName;
   }
   
   /** @param newFirstName
    * @pdOid 1b6eb74d-db78-4719-a604-f6b7565f0ee1 */
   public void setFirstName(java.lang.String newFirstName) {
      firstName = newFirstName;
   }
   
   /** @pdOid 0247f358-8d0a-474a-8833-9e32e106d761 */
   public java.lang.String getLastName() {
      return lastName;
   }
   
   /** @param newLastName
    * @pdOid b1a891ff-355f-438f-bd95-b495cd8b7d04 */
   public void setLastName(java.lang.String newLastName) {
      lastName = newLastName;
   }
   
   /** @pdOid 5263407b-eec2-42e7-9517-35dbced73118 */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid cea9f074-332c-43c1-b8bb-548a249567fd */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }

}