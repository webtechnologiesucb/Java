/***********************************************************************
 * Module:  Hangar.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Hangar
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class Hangar {

   private int nCodHangar;
   private String cDescripcion;
   private boolean bOcupado;
   
   public int getnCodHangar() {
      return nCodHangar;
   }
   
   public void setnCodHangar(int newNCodHangar) {
      nCodHangar = newNCodHangar;
   }
   
   public String getcDescripcion() {
      return cDescripcion;
   }
   
   public void setcDescripcion(String newCDescripcion) {
      cDescripcion = newCDescripcion;
   }
   
   public boolean isbOcupado() {
      return bOcupado;
   }
   
   public void setbOcupado(boolean newBOcupado) {
      bOcupado = newBOcupado;
   }
}