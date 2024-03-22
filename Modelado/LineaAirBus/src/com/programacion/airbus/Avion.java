/***********************************************************************
 * Module:  Avion.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Avion
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;

public class Avion extends Transporte {
   private String cCodigoAvion;
   private String cMatricula;
   
   /** @pdRoleInfo migr=no name=Hangar assc=Association_2 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Hangar> hangar;
   
   public String getcCodigoAvion() {
      return cCodigoAvion;
   }
   
   public void setcCodigoAvion(String newCCodigoAvion) {
      cCodigoAvion = newCCodigoAvion;
   }
   
   public String getcMatricula() {
      return cMatricula;
   }
   
   public void setcMatricula(String newCMatricula) {
      cMatricula = newCMatricula;
   }
   
   public java.util.Collection<Hangar> getHangar() {
      if (hangar == null)
         hangar = new java.util.HashSet<Hangar>();
      return hangar;
   }
   
   public java.util.Iterator<Hangar> getIteratorHangar() {
      if (hangar == null)
         hangar = new java.util.HashSet<Hangar>();
      return hangar.iterator();
   }
   
   public void setHangar(java.util.Collection<Hangar> newHangar) {
      removeAllHangar();
      for (java.util.Iterator<Hangar> iter = newHangar.iterator(); iter.hasNext();)
         addHangar(iter.next());
   }
   
   public void addHangar(Hangar newHangar) {
      if (newHangar == null)
         return;
      if (this.hangar == null)
         this.hangar = new java.util.HashSet<Hangar>();
      if (!this.hangar.contains(newHangar))
         this.hangar.add(newHangar);
   }
   
   public void removeHangar(Hangar oldHangar) {
      if (oldHangar == null)
         return;
      if (this.hangar != null)
         if (this.hangar.contains(oldHangar))
            this.hangar.remove(oldHangar);
   }
   
   public void removeAllHangar() {
      if (hangar != null)
         hangar.clear();
   }
}