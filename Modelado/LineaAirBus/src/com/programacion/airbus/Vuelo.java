/***********************************************************************
 * Module:  Vuelo.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Vuelo
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class Vuelo {

   private String cDestino;
   private int nDuracion;
   private java.util.Date dSalida;
   
   /** @pdRoleInfo migr=no name=Pasaje assc=Association_5 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Pasaje> pasaje;
   /** @pdRoleInfo migr=no name=Tripulacion assc=Association_7 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Tripulacion> tripulacion;

   public String getcDestino() {
      return cDestino;
   }

   public void setcDestino(String newCDestino) {
      cDestino = newCDestino;
   }

   public int getnDuracion() {
      return nDuracion;
   }

   public void setnDuracion(int newNDuracion) {
      nDuracion = newNDuracion;
   }

   public java.util.Date getDSalida() {
      return dSalida;
   }

   public void setdSalida(java.util.Date newDSalida) {
      dSalida = newDSalida;
   }

   public java.util.Collection<Pasaje> getPasaje() {
      if (pasaje == null)
         pasaje = new java.util.HashSet<Pasaje>();
      return pasaje;
   }

   public java.util.Iterator<Pasaje> getIteratorPasaje() {
      if (pasaje == null)
         pasaje = new java.util.HashSet<Pasaje>();
      return pasaje.iterator();
   }

   public void setPasaje(java.util.Collection<Pasaje> newPasaje) {
      removeAllPasaje();
      for (java.util.Iterator<Pasaje> iter = newPasaje.iterator(); iter.hasNext();)
         addPasaje(iter.next());
   }

   public void addPasaje(Pasaje newPasaje) {
      if (newPasaje == null)
         return;
      if (this.pasaje == null)
         this.pasaje = new java.util.HashSet<Pasaje>();
      if (!this.pasaje.contains(newPasaje))
         this.pasaje.add(newPasaje);
   }

   public void removePasaje(Pasaje oldPasaje) {
      if (oldPasaje == null)
         return;
      if (this.pasaje != null)
         if (this.pasaje.contains(oldPasaje))
            this.pasaje.remove(oldPasaje);
   }

   public void removeAllPasaje() {
      if (pasaje != null)
         pasaje.clear();
   }

   public java.util.Collection<Tripulacion> getTripulacion() {
      if (tripulacion == null)
         tripulacion = new java.util.HashSet<Tripulacion>();
      return tripulacion;
   }

   public java.util.Iterator<Tripulacion> getIteratorTripulacion() {
      if (tripulacion == null)
         tripulacion = new java.util.HashSet<Tripulacion>();
      return tripulacion.iterator();
   }

   public void setTripulacion(java.util.Collection<Tripulacion> newTripulacion) {
      removeAllTripulacion();
      for (Object element : newTripulacion)
		addTripulacion((Tripulacion)element);
   }

   public void addTripulacion(Tripulacion newTripulacion) {
      if (newTripulacion == null)
         return;
      if (this.tripulacion == null)
         this.tripulacion = new java.util.HashSet<Tripulacion>();
      if (!this.tripulacion.contains(newTripulacion))
         this.tripulacion.add(newTripulacion);
   }

   public void removeTripulacion(Tripulacion oldTripulacion) {
      if (oldTripulacion == null)
         return;
      if (this.tripulacion != null)
         if (this.tripulacion.contains(oldTripulacion))
            this.tripulacion.remove(oldTripulacion);
   }

   public void removeAllTripulacion() {
      if (tripulacion != null)
         tripulacion.clear();
   }
}