package rmi_estacionamiento;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BarreraSalida extends UnicastRemoteObject implements IBarreraSalida {
// false= barrera baja, true = barrera alta

    boolean EstadoBarrera = false;

    public BarreraSalida() throws RemoteException {
        super();
    }

    @Override
    public void Subir() throws RemoteException {
        EstadoBarrera = true;
        System.out.println("_> Barrera de Entrada  en Alto\n");
    }

    @Override
    public void Bajar() throws RemoteException {
        EstadoBarrera = false;
        System.out.println("_> Barrera de Entrada  Baja\n");
    }
}
