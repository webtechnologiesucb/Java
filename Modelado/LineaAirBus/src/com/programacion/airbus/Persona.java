/***********************************************************************
 * Module:  Persona.java
 * Author:  MIGUEL PACHECO
 * Purpose: Defines the Class Persona
 ***********************************************************************/
package com.programacion.airbus;

import java.util.*;

/**
 * Clase Persona
 */
public class Persona {

   private String cNombre;
   private String cApellido;
   
   /**
    * Obtener el nombre
    * @return Nombre ingresado
    */
   public String getcNombre() {
      return cNombre;
   }
   
   /**
    * Utilizar el nombre
    * @param newCNombre Nombre ingresado
    */
   public void setcNombre(String newCNombre) {
      cNombre = newCNombre;
   }

   /**
    * Obtener el apellido de la persona
    * @return Apellido de la persona
    */
   public String getcApellido() {
      return cApellido;
   }

   /**
    * Utilizar el apellido ingresado
    * @param newCApellido Apellido ingresado
    */
   public void setcApellido(String newCApellido) {
      cApellido = newCApellido;
   }
}