
package rmi_estacionamiento;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Impresora extends UnicastRemoteObject implements IImpresora{
    String mensaje;
    public Impresora() throws RemoteException{
    super();
    }
    @Override
    public void EstablecerTicket(String ticket) throws RemoteException {
        mensaje = ticket;
    }
    @Override
    public void  EmitirTicket() throws RemoteException {
        System.out.println("\n_>"+mensaje);
    }
}
