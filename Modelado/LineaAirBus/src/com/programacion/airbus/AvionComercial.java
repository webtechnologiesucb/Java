/***********************************************************************
 * Module:  AvionComercial.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class AvionComercial
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class AvionComercial extends Avion {

   private String cDenominacion;
   private int nCapacidad;
   
   /** @pdRoleInfo migr=no name=Vuelo assc=Association_4 coll=java.util.List impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.List<Vuelo> vuelo;
   
   public String getcDenominacion() {
      return cDenominacion;
   }
   
   public void setcDenominacion(String newCDenominacion) {
      cDenominacion = newCDenominacion;
   }
   
   public int getnCapacidad() {
      return nCapacidad;
   }
   
   public void setnCapacidad(int newNCapacidad) {
      nCapacidad = newNCapacidad;
   }
   
   public java.util.List<Vuelo> getVuelo() {
      if (vuelo == null)
         vuelo = new java.util.HashSet<Vuelo>();
      return vuelo;
   }
   
   public java.util.Iterator<Vuelo> getIteratorVuelo() {
      if (vuelo == null)
         vuelo = new java.util.HashSet<Vuelo>();
      return vuelo.iterator();
   }
   
   public void setVuelo(java.util.List<Vuelo> newVuelo) {
      removeAllVuelo();
      for (java.util.Iterator<Vuelo> iter = newVuelo.iterator(); iter.hasNext();)
         addVuelo(iter.next());
   }
   
   public void addVuelo(Vuelo newVuelo) {
      if (newVuelo == null)
         return;
      if (this.vuelo == null)
         this.vuelo = new java.util.HashSet<Vuelo>();
      if (!this.vuelo.contains(newVuelo))
         this.vuelo.add(newVuelo);
   }
   
   public void removeVuelo(Vuelo oldVuelo) {
      if (oldVuelo == null)
         return;
      if (this.vuelo != null)
         if (this.vuelo.contains(oldVuelo))
            this.vuelo.remove(oldVuelo);
   }
   
   public void removeAllVuelo() {
      if (vuelo != null)
         vuelo.clear();
   }
}