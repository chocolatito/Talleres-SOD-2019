package rmi_estacionamiento;

import interfaces.IParlante;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Parlante extends UnicastRemoteObject implements IParlante {

    String mensaje;

    public Parlante() throws RemoteException {
        super();
    }

    @Override
    public void EstablecerMensaje(String msg) throws RemoteException {
        mensaje = msg;
    }

    @Override
    public void TransmitirMensaje() throws RemoteException {
        System.out.println(mensaje);
    }
}
