/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_estacionamiento;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SemaforoEntrada extends UnicastRemoteObject implements ISemaforoEntrada {

    boolean EstadoLuz = false;

    // false= rojo, true = verde
    public SemaforoEntrada() throws RemoteException {
        super();
    }

    @Override
    public void EncenderVerde() throws RemoteException {
        EstadoLuz = true;
        System.out.println("_> Luz Verde Encendida\n");
    }

    @Override
    public void EncenderRoja() throws RemoteException {
        EstadoLuz = false;
        System.out.println("_> Luz Roja Encendida\n");
    }
}
