/***********************************************************************
 * Module:  Tripulacion.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Tripulacion
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;

public class Tripulacion {

   private int nTotalTripulacion;
   
   public java.util.Collection<Azafata> azafata;
   public java.util.Collection<Piloto> piloto;
   public java.util.Collection<Copiloto> copiloto;
   public java.util.Collection<Pasajero> pasajero;
   
   public int getnTotalTripulacion() {
      return nTotalTripulacion;
   }
   
   public void setnTotalTripulacion(int newNTotalTripulacion) {
      nTotalTripulacion = newNTotalTripulacion;
   }
   
   public java.util.Collection<Azafata> getAzafata() {
      if (azafata == null)
         azafata = new java.util.HashSet<Azafata>();
      return azafata;
   }

   public java.util.Iterator<Azafata> getIteratorAzafata() {
      if (azafata == null)
         azafata = new java.util.HashSet<Azafata>();
      return azafata.iterator();
   }
   
   public void setAzafata(java.util.Collection<Azafata> newAzafata) {
      removeAllAzafata();
      for (Object element : newAzafata)
		addAzafata((Azafata)element);
   }

   public void addAzafata(Azafata newAzafata) {
      if (newAzafata == null)
         return;
      if (this.azafata == null)
         this.azafata = new java.util.HashSet<Azafata>();
      if (!this.azafata.contains(newAzafata))
         this.azafata.add(newAzafata);
   }

   public void removeAzafata(Azafata oldAzafata) {
      if (oldAzafata == null)
         return;
      if (this.azafata != null)
         if (this.azafata.contains(oldAzafata))
            this.azafata.remove(oldAzafata);
   }

   public void removeAllAzafata() {
      if (azafata != null)
         azafata.clear();
   }

   public java.util.Collection<Piloto> getPiloto() {
      if (piloto == null)
         piloto = new java.util.HashSet<Piloto>();
      return piloto;
   }

   public java.util.Iterator<Piloto> getIteratorPiloto() {
      if (piloto == null)
         piloto = new java.util.HashSet<Piloto>();
      return piloto.iterator();
   }

   public void setPiloto(java.util.Collection<Piloto> newPiloto) {
      removeAllPiloto();
      for (java.util.Iterator<Piloto> iter = newPiloto.iterator(); iter.hasNext();)
         addPiloto(iter.next());
   }

   public void addPiloto(Piloto newPiloto) {
      if (newPiloto == null)
         return;
      if (this.piloto == null)
         this.piloto = new java.util.HashSet<Piloto>();
      if (!this.piloto.contains(newPiloto))
         this.piloto.add(newPiloto);
   }

   public void removePiloto(Piloto oldPiloto) {
      if (oldPiloto == null)
         return;
      if (this.piloto != null)
         if (this.piloto.contains(oldPiloto))
            this.piloto.remove(oldPiloto);
   }

   public void removeAllPiloto() {
      if (piloto != null)
         piloto.clear();
   }

   public java.util.Collection<Copiloto> getCopiloto() {
      if (copiloto == null)
         copiloto = new java.util.HashSet<Copiloto>();
      return copiloto;
   }

   public java.util.Iterator<Copiloto> getIteratorCopiloto() {
      if (copiloto == null)
         copiloto = new java.util.HashSet<Copiloto>();
      return copiloto.iterator();
   }

   public void setCopiloto(java.util.Collection<Copiloto> newCopiloto) {
      removeAllCopiloto();
      for (java.util.Iterator<Copiloto> iter = newCopiloto.iterator(); iter.hasNext();)
         addCopiloto(iter.next());
   }

   public void addCopiloto(Copiloto newCopiloto) {
      if (newCopiloto == null)
         return;
      if (this.copiloto == null)
         this.copiloto = new java.util.HashSet<Copiloto>();
      if (!this.copiloto.contains(newCopiloto))
         this.copiloto.add(newCopiloto);
   }

   public void removeCopiloto(Copiloto oldCopiloto) {
      if (oldCopiloto == null)
         return;
      if (this.copiloto != null)
         if (this.copiloto.contains(oldCopiloto))
            this.copiloto.remove(oldCopiloto);
   }

   public void removeAllCopiloto() {
      if (copiloto != null)
         copiloto.clear();
   }

   public java.util.Collection<Pasajero> getPasajero() {
      if (pasajero == null)
         pasajero = new java.util.HashSet<Pasajero>();
      return pasajero;
   }

   public java.util.Iterator<Pasajero> getIteratorPasajero() {
      if (pasajero == null)
         pasajero = new java.util.HashSet<Pasajero>();
      return pasajero.iterator();
   }

   public void setPasajero(java.util.Collection<Pasajero> newPasajero) {
      removeAllPasajero();
      for (java.util.Iterator<Pasajero> iter = newPasajero.iterator(); iter.hasNext();)
         addPasajero(iter.next());
   }

   public void addPasajero(Pasajero newPasajero) {
      if (newPasajero == null)
         return;
      if (this.pasajero == null)
         this.pasajero = new java.util.HashSet<Pasajero>();
      if (!this.pasajero.contains(newPasajero))
         this.pasajero.add(newPasajero);
   }

   public void removePasajero(Pasajero oldPasajero) {
      if (oldPasajero == null)
         return;
      if (this.pasajero != null)
         if (this.pasajero.contains(oldPasajero))
            this.pasajero.remove(oldPasajero);
   }

   public void removeAllPasajero() {
      if (pasajero != null)
         pasajero.clear();
   }
}