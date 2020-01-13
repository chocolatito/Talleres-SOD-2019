package rmi_estacionamiento;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Sirena extends UnicastRemoteObject implements ISirena {
// false=  Detenida, true = Reproducioendo

    boolean EstadoSirena = false;

    public Sirena() throws RemoteException {
        super();
    }

    @Override
    public void Reproducir() throws RemoteException {
        EstadoSirena = true;
        System.out.println("_> Sirena Sonando\n");
    }

    @Override
    public void Detener() throws RemoteException {
        EstadoSirena = false;
        System.out.println("_> Sirena Dejo de Sonar\n");
    }
}
