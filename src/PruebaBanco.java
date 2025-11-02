import java.util.Scanner;

public class PruebaBanco {

    public static void main(String[] args) {

//        La clase principal debe crear una cuenta bancaria, así como el número de clientes
//        que indique el usuario (por teclado) e iniciarlos de manera que todos operen sobre
//        la cuenta bancaria compartida.

        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el numero de clientes: ");
        int numClientes = teclado.nextInt();
        System.out.println();

        CuentaBancaria cuenta = new CuentaBancaria();

        Cliente[] clientes = new Cliente[numClientes];

        for (int i = 0; i < numClientes; i++) {
            clientes[i] = new Cliente("Cliente - " + (i + 1), cuenta);
            clientes[i].start();
        }

        for (Cliente c: clientes){
            try {
                c.join();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        int totalDepositos = 0;
        int totalRetiros = 0;

        for (Cliente c : clientes) {
            totalDepositos += c.getTotalDepositado();
            totalRetiros += c.getTotalRetirado();
        }

        int saldoEsperado = totalDepositos - totalRetiros;

        System.out.println("\nSaldo final real: " + cuenta.getSaldo() + "€");
        System.out.println("Saldo esperado: " + saldoEsperado + "€");

    }

}
