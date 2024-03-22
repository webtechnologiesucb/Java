/***********************************************************************
 * Module:  LineaArea.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class LineaArea
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class LineaArea {

   private java.util.Date dInicioActividades;
   private String cNombre;
   
   /** @pdRoleInfo migr=no name=AvionComercial assc=Association_3 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<AvionComercial> avionComercial;

   public java.util.Date getDInicioActividades() {
      return dInicioActividades;
   }

   public void setdInicioActividades(java.util.Date newDInicioActividades) {
      dInicioActividades = newDInicioActividades;
   }
   
   public String getcNombre() {
      return cNombre;
   }
   
   public void setcNombre(String newCNombre) {
      cNombre = newCNombre;
   }
   
   public java.util.Collection<AvionComercial> getAvionComercial() {
      if (avionComercial == null)
         avionComercial = new java.util.HashSet<AvionComercial>();
      return avionComercial;
   }
   
   public java.util.Iterator<AvionComercial> getIteratorAvionComercial() {
      if (avionComercial == null)
         avionComercial = new java.util.HashSet<AvionComercial>();
      return avionComercial.iterator();
   }
   
   public void setAvionComercial(java.util.Collection<AvionComercial> newAvionComercial) {
      removeAllAvionComercial();
      for (Object element : newAvionComercial)
		addAvionComercial((AvionComercial)element);
   }
   
   public void addAvionComercial(AvionComercial newAvionComercial) {
      if (newAvionComercial == null)
         return;
      if (this.avionComercial == null)
         this.avionComercial = new java.util.HashSet<AvionComercial>();
      if (!this.avionComercial.contains(newAvionComercial))
         this.avionComercial.add(newAvionComercial);
   }
   
   public void removeAvionComercial(AvionComercial oldAvionComercial) {
      if (oldAvionComercial == null)
         return;
      if (this.avionComercial != null)
         if (this.avionComercial.contains(oldAvionComercial))
            this.avionComercial.remove(oldAvionComercial);
   }
   
   public void removeAllAvionComercial() {
      if (avionComercial != null)
         avionComercial.clear();
   }
}