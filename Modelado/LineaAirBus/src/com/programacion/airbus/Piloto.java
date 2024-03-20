/***********************************************************************
 * Module:  Piloto.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Piloto
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class Piloto extends Persona {

   private int nHorasVuelo;
   private boolean bTitulado;
   private boolean bDisponible;

   public int getnHorasVuelo() {
      return nHorasVuelo;
   }

   public void setnHorasVuelo(int newNHorasVuelo) {
      nHorasVuelo = newNHorasVuelo;
   }

   public boolean isbTitulado() {
      return bTitulado;
   }

   public void setbTitulado(boolean newBTitulado) {
      bTitulado = newBTitulado;
   }

   public boolean isbDisponible() {
      return bDisponible;
   }
   
   public void setbDisponible(boolean newBDisponible) {
      bDisponible = newBDisponible;
   }
}