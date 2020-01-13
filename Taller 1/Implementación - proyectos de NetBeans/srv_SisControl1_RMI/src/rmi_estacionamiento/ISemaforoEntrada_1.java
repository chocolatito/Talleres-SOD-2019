package rmi_estacionamiento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISemaforoEntrada_1 extends Remote {

    public void EncenderVerde() throws RemoteException;

    public void EncenderRoja() throws RemoteException;

}
