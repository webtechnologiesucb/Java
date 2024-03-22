/***********************************************************************
 * Module:  Pasajero.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Pasajero
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class Pasajero extends Persona {

   private int nCodPasajero;
   private String cNombre;
   private boolean bMenorEdad;
   
   /** @pdRoleInfo migr=no name=Pasaje assc=Association_11 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Pasaje> pasaje;
   
   public int getnCodPasajero() {
      return nCodPasajero;
   }

   public void setnCodPasajero(int newNCodPasajero) {
      nCodPasajero = newNCodPasajero;
   }

   public String getcNombre() {
      return cNombre;
   }
   
   public void setcNombre(String newCNombre) {
      cNombre = newCNombre;
   }

   public boolean isbMenorEdad() {
      return bMenorEdad;
   }

   public void setbMenorEdad(boolean newBMenorEdad) {
      bMenorEdad = newBMenorEdad;
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
      for (Object element : newPasaje)
		addPasaje((Pasaje)element);
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
}