package rmi_estacionamiento;

import interfaces.IClienteSensorTicket;
import interfaces.ISemaforoEntrada;
import interfaces.IBarreraEntrada;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteSensorTicket extends UnicastRemoteObject implements IClienteSensorTicket, Serializable {

    IBarreraEntrada objBarreraEntrada = null;
    ISemaforoEntrada objSemaforoEntrada = null;

    public ClienteSensorTicket() throws RemoteException {
        System.setSecurityManager(new SecurityManager());
        try {
            //LocateRegistry.createRegistry(1099);     
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        try {
            String servidorSemaforoEntrada = "rmi://localhost:1104/semaforoEntrada";
            objSemaforoEntrada = (ISemaforoEntrada) Naming.lookup(servidorSemaforoEntrada);
            String servidorBarreraEntrada = "rmi://localhost:1105/barreraEntrada";
            objBarreraEntrada = (IBarreraEntrada) Naming.lookup(servidorBarreraEntrada);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteSensorTicket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteSensorTicket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void RecibirNotificacionEvento() throws RemoteException {
        System.out.println("_> Se extrajo el Ticket\n");

        try {
            objSemaforoEntrada.EncenderRoja();
            objBarreraEntrada.Subir();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClienteSensorEntrada";
    }
}
