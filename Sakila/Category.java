/***********************************************************************
 * Module:  Category.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Category
 ***********************************************************************/

import java.util.*;

/** @pdOid 2963eb38-7f96-490d-a074-87401065596d */
public class Category {
   /** @pdOid 69c95e9b-55c7-4569-9cdd-af30db28c763 */
   public long categoryId;
   /** @pdOid b852d530-33b4-4d6d-ac6a-9a52292edff4 */
   public java.lang.String name;
   /** @pdOid 0064e780-609e-4684-9f18-e07e41517955 */
   public java.util.Date lastUpdate;
   
   public java.util.Collection filmCategory;
   
   /** @pdOid 429fdcd0-b31f-46b4-b99a-ed0d93ae7c3a */
   public long getCategoryId() {
      return categoryId;
   }
   
   /** @param newCategoryId
    * @pdOid 2fa1432a-0932-489d-a3ca-87957c6331c1 */
   public void setCategoryId(long newCategoryId) {
      categoryId = newCategoryId;
   }
   
   /** @pdOid e3faac1d-7bef-4ff6-8681-e84b1c099f5e */
   public java.lang.String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid 8a42f890-2268-433c-9236-8bb5b80a760e */
   public void setName(java.lang.String newName) {
      name = newName;
   }
   
   /** @pdOid 6ba4d9c0-372f-4266-b1ee-a5fc9725a5bf */
   public java.util.Date getLastUpdate() {
      return lastUpdate;
   }
   
   /** @param newLastUpdate
    * @pdOid 5799cf0d-adfb-475b-963a-da8fbf890490 */
   public void setLastUpdate(java.util.Date newLastUpdate) {
      lastUpdate = newLastUpdate;
   }

}