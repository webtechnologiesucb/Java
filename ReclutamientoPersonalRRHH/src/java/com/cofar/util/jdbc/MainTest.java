/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.util.jdbc;


import java.sql.SQLException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 14-05-2015 time 06:53:17 PM
 */
public class MainTest {

    public static int getDomingos(int annoInicial, int annoFinal) {
        int totalDomingos = 0;
        Calendar fechaInicial = new GregorianCalendar(annoInicial, 1 - 1, 01);

        Calendar fechaFinal = new GregorianCalendar(annoFinal, 12 - 1, 31);

        while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
            if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_MONTH) == 1) {
                totalDomingos++;
            }
            //se suma 1 dia para hacer la validacion del siguiente dia.

            fechaInicial.add(Calendar.DATE, 1);

        }

        return totalDomingos;
    }

    private static String iniciales(String nombre) {
        String pass = "";
        int posicion = 1;
        java.util.StringTokenizer st = new java.util.StringTokenizer(nombre);
        while (st.hasMoreTokens()) {
            String val = st.nextToken();
            pass += val.charAt(0);
            pass += posicion;
            posicion += val.length() + 1;

        }
        return pass;
    }

    private static int cuadradosSerie(int n) {
        int suma = 0;
        int cuadrados = 0;
        for (int i = 1; i <= n; i++) {
            cuadrados += Math.pow(i, 2);
            suma += i;
        }
        suma = (int) Math.pow(suma, 2);
        return suma - cuadrados;
    }

    private static int veintiunCaracteres(String data) {

        int mayor = 0;
        for (int i = 1; i <= 7; i++) {
            int valor = Integer.parseInt(data.substring(i * 3 - 3, i * 3));
            System.out.println("valor " + valor);
            if (valor > mayor) {
                mayor = valor;
            }
        }
        return mayor;

    }

    public static void main(String[] args) throws SQLException {
        /* ConnectionDataBase conn=new ConnectionDataBase();
         Connection cnConnection=conn.getConnectionDataBase_Sartorius_test1();
         Statement statement=cnConnection.createStatement();
         String query="exec [USP_GET_LISTA_INGRESOS] "+"1"+",10";
         ResultSet rs= statement.executeQuery(query);
         System.out.println("vv  "+query);
         if (rs.next())
         System.out.println("valor: "+rs.getString(1));
         cnConnection.close();
         double valor=0;
         for (int i = 0; i < 10; i++) {
         System.out.println("suma: "+valor);
         valor+=(6/Math.pow(16,i+1));
         }
         valor*=5;
         System.out.println("res: "+valor);
         EventsView eventsView=new EventsView();
         eventsView.init();*/
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, 2016);
        GregorianCalendar calendar1 = (GregorianCalendar) GregorianCalendar.getInstance();
        int d = GregorianCalendar.DAY_OF_WEEK;
        System.out.println("");
        System.out.println(MainTest.getDomingos(2012, 2015));
        System.out.println(iniciales("Alfredo Lugones Perez"));
        System.out.println(cuadradosSerie(16));
        System.out.println(veintiunCaracteres("875541932741236523125"));

        int n = 10;
        int suma = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                suma = suma + i;
            }
        }
        System.out.println("suma "+suma);
        
        int nn=10;
        
    }
}
