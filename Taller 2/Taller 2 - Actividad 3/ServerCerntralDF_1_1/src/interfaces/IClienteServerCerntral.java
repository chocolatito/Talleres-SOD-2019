package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClienteServerCerntral extends Remote {

    public void IngresarSeccionCritica() throws RemoteException;
    public String RetornarID() throws RemoteException;
}