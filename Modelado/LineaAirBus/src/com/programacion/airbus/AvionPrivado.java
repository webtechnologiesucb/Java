/***********************************************************************
 * Module:  AvionPrivado.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class AvionPrivado
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class AvionPrivado extends Avion {

   private String cPropietario;
   private String cDenominacion;
   
   public String getcPropietario() {
      return cPropietario;
   }
   
   public void setcPropietario(String newCPropietario) {
      cPropietario = newCPropietario;
   }
   
   public String getcDenominacion() {
      return cDenominacion;
   }
   
   public void setcDenominacion(String newCDenominacion) {
      cDenominacion = newCDenominacion;
   }
}