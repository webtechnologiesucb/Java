/***********************************************************************
 * Module:  Persona.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Persona
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;


public class Persona {

   private String cNombre;
   private String cApellido;
   
   public String getcNombre() {
      return cNombre;
   }
   
   public void setcNombre(String newCNombre) {
      cNombre = newCNombre;
   }

   public String getcApellido() {
      return cApellido;
   }

   public void setcApellido(String newCApellido) {
      cApellido = newCApellido;
   }
}