
package rmi_estacionamiento;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IParlante extends Remote {

    public void EstablecerMensaje(String msg) throws RemoteException;

    public void TransmitirMensaje() throws RemoteException;
}
