/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_estacionamiento;

import java.util.Scanner;

class SimuladorContacto extends Thread{
    Contacto contacto;
    public SimuladorContacto(Contacto c){
        contacto = c;
        start();
    }
    @Override
    public void run(){
        Scanner entrada = new Scanner(System.in);
        while(true){
            System.out.println("Ingrese 1 señalar contacto SE");
            short tipoC = entrada.nextShort();
            entrada.nextLine();
            if (tipoC ==1){
                contacto.NotificarContacto();
            }
        }
    }
}
