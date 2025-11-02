public class CuentaBancaria {

//    La clase CuentaBancaria debe tener métodos para:
//            • Devolver el saldo actual.
//            • Retirar dinero.
//            • Depositar dinero.
//            • Se deberá poder fijar con una constante el saldo inicial de la cuenta.
    private final int SALDO_INICIAL = 0;

    private int saldo = SALDO_INICIAL;

    synchronized public void saldoActual(){
        System.out.println("El saldo actual es: "+this.saldo+"€");
    }

    synchronized public void retirarSaldo(String nombre, int numRetirar){
        if ((this.saldo - numRetirar) < 0){
            System.out.println(nombre+": no hay suficiente saldo para retirar: "+numRetirar+"€, se han retirado: "+this.saldo+"€");
            this.saldo = 0;
            saldoActual();
        } else {
            System.out.println(nombre+": se han retirado: "+numRetirar+"€");
            this.saldo -= numRetirar;
            saldoActual();
        }
    }

    synchronized public void depositar(String nombre, int numDepositar){
        System.out.println(nombre+": "+numDepositar+"€ depositados correctamente");
        this.saldo += numDepositar;
        saldoActual();
    }

    synchronized public int getSaldo() {
        return this.saldo;
    }

}
