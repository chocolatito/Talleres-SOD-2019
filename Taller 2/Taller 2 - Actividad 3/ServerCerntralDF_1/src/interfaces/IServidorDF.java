package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorDF extends Remote{
    public void RecibirMensaje(int idPs) throws RemoteException;
    public void RecibirEleccion(int idPs) throws RemoteException;
    public void RecibirElegido(int idPs) throws RemoteException;
}
