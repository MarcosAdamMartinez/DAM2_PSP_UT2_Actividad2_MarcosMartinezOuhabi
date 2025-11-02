import java.util.Random;

public class Cliente extends Thread{

    private CuentaBancaria cb;
    private String nombre;
    private Random random = new Random();
    private static final int MAX_OP_CLIENTE = 5;
    private static final int MAX_CANT_DEPOSITAR = 5000;
    private static final int MAX_CANT_RETIRAR = 1000;
    private static final int MAX_TIEMPO_PAUSA = 5000;

    private int totalDepositado = 0;
    private int totalRetirado = 0;

    public Cliente(String nombre, CuentaBancaria cb){
        this.nombre = nombre;
        this.cb = cb;
    }

//    La clase Cliente es, en este caso, el Thread y:
//            • Realiza un bucle con una cantidad y una tipología aleatoria de operaciones
//            (podrán ser de retirada o de depósito de dinero) con cantidades y pausas
//    también aleatorias.
//            • Se deberá poder fijar máximos como valores constantes para el máximo
//    número de operaciones del cliente, la máxima cantidad de una operación y el
//    máximo tiempo de pausa.
//            • Cada operación que realice un cliente debe ser mostrada por consola, con el
//    nombre del cliente, el tipo de operación realizada y la cantidad
//    retirada/depositada correctamente.

    @Override
    synchronized public void run() {
        int numOp = random.nextInt(1, MAX_OP_CLIENTE);

        for (int i = 0; i < numOp; i++) {
            if (random.nextBoolean() == true) {
                int cantidad = random.nextInt(1, MAX_CANT_DEPOSITAR);
                cb.depositar(this.nombre, cantidad);
                totalDepositado += cantidad;

                try {
                    Thread.sleep(random.nextInt(100, MAX_TIEMPO_PAUSA));
                } catch (Exception e){
                    e.printStackTrace();
                }

            } else {
                int cantidad = random.nextInt(1, MAX_CANT_RETIRAR);
                int saldoAntes = cb.getSaldo();
                int saldoDespues;

                synchronized (cb) {
                    cb.retirarSaldo(this.nombre, cantidad);
                    saldoDespues = cb.getSaldo();
                }

                if ((saldoAntes - saldoDespues) > 0){
                    totalRetirado += saldoAntes - saldoDespues;
                }

                try {
                    Thread.sleep(random.nextInt(100, MAX_TIEMPO_PAUSA));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized public int getTotalDepositado() {
        return totalDepositado;
    }

    synchronized public int getTotalRetirado() {
        return totalRetirado;
    }

}
