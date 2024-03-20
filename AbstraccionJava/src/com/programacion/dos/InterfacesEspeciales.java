package com.programacion.dos;

import java.text.DecimalFormat;

interface Aritmetica{
	public final String[] kUnidades = {"", "Un", "Dos", 
			"Tres", "Cuatro", "Cinco", "Seis", 
			"Siete", "Ocho", "Nueve"};
	
	int raizCubica(int n);
	String literal(int i);
	String literalDec(double i, boolean soloEntero);
	
	default int rad2deg(double radian) {
		return (int)(radian * 180 / Math.PI);
	}
	
	default int rad2grad(double radian) {
		return grad(radian);
	}
	
	private int grad(double radian) {
		return (int)(radian * 200 / Math.PI);
	}
}

class Matematica implements Aritmetica{
	@Override
	public String literal(int i) {
		String sMill, sCen, sMil, sDecUni;
        short digUni, digsMill, digCen, digsMil, digDec, digDecUni;
        int entero = i;
        
        digsMill = (short) ((entero % 1_000_000_000) / 1_000_000);
        digsMil = (short) ((entero % 1_000_000) / 1000);
        digCen = (short) ((entero % 1000) / 100);
        digDec = (short) ((entero % 100) / 10);
        digUni = (short) (entero % 10);
        digDecUni = (short) (entero % 100);
        
        sMill = switch (digsMill) {
            case 0 -> "";
            case 1 -> "Un Millón ";
            default -> literalDec(digsMill, true) + " Millones ";
        };
        
        sMil = digsMil == 0 ? "" : literalDec(digsMil, true) + " Mil ";
        
        String[] centenas = {"", "Ciento ", "Doscientos ", "Trescientos ", 
        		"Cuatrocientos ", "Quinientos ", "Seiscientos ", 
        		"Setecientos ", "Ochocientos ", "Novecientos "};
        sCen = centenas[digCen];
        if (digCen == 1 && digDecUni == 0) sCen = "Cien"; // Si acaba en 100
        
        String[] decenas = {"", "Diez", "Veinte", "Treinta", "Cuarenta", 
        		"Cincuenta", "Sesenta", "Setenta", "Ochenta", "Noventa"};
        sDecUni = decenas[digDec];
        if (digUni > 0) { // Para que acabe en una unidad
            if (digDec > 0) sDecUni += " y "; // Por ejemplo Treinta y
            sDecUni += kUnidades[digUni];
        }
        
        if (digUni > 0 && digDec == 1) {
            String[] especiales = {"Once", "Doce", "Trece", "Catorce", 
            		"Quince", "Dieciseis", "Diecisiete", 
            		"Dieciocho", "Diecinueve"};
            sDecUni = especiales[digUni - 1];
        }
        
        if (digUni > 0 && digDec == 2) {
            String[] veintes = {"Veintiun", "Veintidos", "Veintitres", 
            		"Veinticuatro", "Veinticinco", "Veintiseis", 
            		"Veintisiete", "Veintiocho", "Veintinueve"};
            sDecUni = veintes[digUni - 1];
        }
        
        return (sMill + sMil + sCen + sDecUni).trim();
	}
	
	@Override
	public String literalDec(double i, boolean soloEntero) {
		String sMill, sCen, sMil, sDecUni;
        short digUni, digsMill, digCen, digsMil, digDec, digDecUni;
        String sFraccion;
        int entero;
        short fraccion;
        
        entero = (int) Math.floor(i);
        DecimalFormat df = new DecimalFormat("###.##");
        String data =  df.format(((i - entero) * 100.0)/100.0);
        fraccion = (short) (Double.parseDouble(data.replace(',','.')) * 100.0);
        sFraccion = soloEntero ? "" : " " + df.format(fraccion) + "/100";
        
        digsMill = (short) ((entero % 1_000_000_000) / 1_000_000);
        digsMil = (short) ((entero % 1_000_000) / 1000);
        digCen = (short) ((entero % 1000) / 100);
        digDec = (short) ((entero % 100) / 10);
        digUni = (short) (entero % 10);
        digDecUni = (short) (entero % 100);
        
        sMill = switch (digsMill) {
            case 0 -> "";
            case 1 -> "Un Millón ";
            default -> literalDec(digsMill, true) + " Millones ";
        };
        
        sMil = digsMil == 0 ? "" : literalDec(digsMil, true) + " Mil ";
        
        String[] centenas = {"", "Ciento ", "Doscientos ", "Trescientos ", 
        		"Cuatrocientos ", "Quinientos ", "Seiscientos ", 
        		"Setecientos ", "Ochocientos ", "Novecientos "};
        sCen = centenas[digCen];
        if (digCen == 1 && digDecUni == 0) sCen = "Cien"; // Si acaba en 100
        
        String[] decenas = {"", "Diez", "Veinte", "Treinta", "Cuarenta", 
        		"Cincuenta", "Sesenta", "Setenta", "Ochenta", "Noventa"};
        sDecUni = decenas[digDec];
        if (digUni > 0) { // Para que acabe en una unidad
            if (digDec > 0) sDecUni += " y "; // Por ejemplo Treinta y
            sDecUni += kUnidades[digUni];
        }
        
        if (digUni > 0 && digDec == 1) {
            String[] especiales = {"Once", "Doce", "Trece", "Catorce", 
            		"Quince", "Dieciseis", "Diecisiete", 
            		"Dieciocho", "Diecinueve"};
            sDecUni = especiales[digUni - 1];
        }
        
        if (digUni > 0 && digDec == 2) {
            String[] veintes = {"Veintiun", "Veintidos", "Veintitres", 
            		"Veinticuatro", "Veinticinco", "Veintiseis", 
            		"Veintisiete", "Veintiocho", "Veintinueve"};
            sDecUni = veintes[digUni - 1];
        }
        
        return (sMill + sMil + sCen + sDecUni).trim() + sFraccion;
	}
	
	@Override
	public int raizCubica(int n) {
		return (int)Math.pow(n, 1.0/3.0);
	}
	
}

public class InterfacesEspeciales {
	public static void main(String ... args) {
		Matematica m = new Matematica();
		System.out.println("Raiz Cubica: " + m.raizCubica(8));
		System.out.println("Literal: " + m.literal(2555));
		System.out.println("Literal Decimal: " + m.literalDec(2555.89, false));
		System.out.println("Radianes a grados: " + m.rad2deg(1.0472));
		System.out.println("Radianes a grados centesimales: " + m.rad2grad(1.0472));
	}
}
