/***********************************************************************
 * Module:  Azafata.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Azafata
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class Azafata extends Persona {

   private int nDiasTrabajados;
   private boolean bLibre;
   private boolean bEnVuelo;
   
   public int getnDiasTrabajados() {
      return nDiasTrabajados;
   }
   
   public void setnDiasTrabajados(int newNDiasTrabajados) {
      nDiasTrabajados = newNDiasTrabajados;
   }
   
   public boolean isbLibre() {
      return bLibre;
   }
   
   public void setbLibre(boolean newBLibre) {
      bLibre = newBLibre;
   }
   
   public boolean isbEnVuelo() {
      return bEnVuelo;
   }
   
   public void setbEnVuelo(boolean newBEnVuelo) {
      bEnVuelo = newBEnVuelo;
   }
}