package rmi_estacionamiento;

import java.util.Scanner;

class SimuladorContacto extends Thread {

    Contacto contacto;

    public SimuladorContacto(Contacto c) {
        contacto = c;
        start();
    }

    @Override
    public void run() {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese 5 señalar contacto SS");
            short tipoC = entrada.nextShort();
            entrada.nextLine();
            if (tipoC == 5) {
                contacto.NotificarContacto();
            }
        }
    }
}
