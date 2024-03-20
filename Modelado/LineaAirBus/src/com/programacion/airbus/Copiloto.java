/***********************************************************************
 * Module:  Copiloto.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Copiloto
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class Copiloto extends Persona {

   private int nHorasPractica;
   private boolean bEgresado;
   private boolean bPracticas;
   
   public int getnHorasPractica() {
      return nHorasPractica;
   }
   
   public void setnHorasPractica(int newNHorasPractica) {
      nHorasPractica = newNHorasPractica;
   }
   
   public boolean isbEgresado() {
      return bEgresado;
   }
   
   public void setbEgresado(boolean newBEgresado) {
      bEgresado = newBEgresado;
   }
   
   public boolean isbPracticas() {
      return bPracticas;
   }
   
   public void setbPracticas(boolean newBPracticas) {
      bPracticas = newBPracticas;
   }
}