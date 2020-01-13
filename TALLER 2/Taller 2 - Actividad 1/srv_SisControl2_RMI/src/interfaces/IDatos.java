
package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDatos extends Remote {
    void Incrementar() throws RemoteException;
     void Decrementar() throws RemoteException;
     public boolean Disponible() throws RemoteException;
}