package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerCerntral extends Remote {

    public void Solicitar(IClienteServerCerntral cliente) throws RemoteException;

    public void Liberar() throws RemoteException;

}
