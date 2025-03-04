public class Ejercicio4 {
	//CuentaBancaria
    private double saldo = 1000;

    public static void main(String[] args) {
    	Ejercicio4 cuenta = new Ejercicio4();
        Thread deposito = new Thread(new Operacion(cuenta, "deposito", 500));
        Thread retiro = new Thread(new Operacion(cuenta, "retiro", 300));

        deposito.start();
        retiro.start();
    }

    public synchronized void depositar(double cantidad) {
        saldo += cantidad;
        System.out.println("Depósito de " + cantidad + " realizado. Saldo actual: " + saldo);
    }

    public synchronized void retirar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retiro de " + cantidad + " realizado. Saldo actual: " + saldo);
        } else {
            System.out.println("Fondos insuficientes para retiro de " + cantidad + ". Saldo actual: " + saldo);
        }
    }
}

class Operacion implements Runnable {
    private Ejercicio4 cuenta;
    private String tipo;
    private double cantidad;

    public Operacion(Ejercicio4 cuenta, String tipo, double cantidad) {
        this.cuenta = cuenta;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        if (tipo.equals("deposito")) {
            cuenta.depositar(cantidad);
        } else if (tipo.equals("retiro")) {
            cuenta.retirar(cantidad);
        }
    }
}
