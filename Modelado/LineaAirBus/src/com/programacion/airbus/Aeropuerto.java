/***********************************************************************
 * Module:  Aeropuerto.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Aeropuerto
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;

public class Aeropuerto {
   private String cCodigoInternacional;
   private String cDescripcion;
   
   public java.util.List<Hangar> hangar;
   
   public String getcCodigoInternacional() {
      return cCodigoInternacional;
   }
   
   public void setcCodigoInternacional(String newCCodigoInternacional) {
      cCodigoInternacional = newCCodigoInternacional;
   }
   
   public String getcDescripcion() {
      return cDescripcion;
   }
   
   public void setcDescripcion(String newCDescripcion) {
      cDescripcion = newCDescripcion;
   }
   
   public java.util.List<Hangar> getHangar() {
      if (hangar == null)
         hangar = (List<Hangar>) new java.util.HashSet<Hangar>();
      return hangar;
   }
   
   public java.util.Iterator<Hangar> getIteratorHangar() {
      if (hangar == null)
         hangar = (List<Hangar>) new java.util.HashSet<Hangar>();
      return hangar.iterator();
   }
   
   public void setHangar(java.util.List<Hangar> newHangar) {
      removeAllHangar();
      for (java.util.Iterator<Hangar> iter = newHangar.iterator(); iter.hasNext();)
         addHangar(iter.next());
   }
   
   public void addHangar(Hangar newHangar) {
      if (newHangar == null)
         return;
      if (this.hangar == null)
         this.hangar = (List<Hangar>) new java.util.HashSet<Hangar>();
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