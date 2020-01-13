package rmi_estacionamiento;

import interfaces.ISirena;
import interfaces.IBarreraSalida;
import interfaces.IClienteCabinaCobro;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteCabinaCobro extends UnicastRemoteObject implements IClienteCabinaCobro, Serializable {

    public ClienteCabinaCobro() throws RemoteException {
        super();
    }

    @Override
    public void RecibirNotificacionEvento() throws RemoteException {
        System.out.println("Se efectuo el cobro");

        IBarreraSalida objBarreraSalida = null;
        ISirena objSirena = null;
        System.setSecurityManager(new SecurityManager());
        try {
            //LocateRegistry.createRegistry(1099);     
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteCabinaCobro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String servidorSirena = "rmi://localhost:1108/sirena";
            objSirena = (ISirena) Naming.lookup(servidorSirena);
            String servidorBarreraSalida = "rmi://localhost:1109/barreraSalida";
            objBarreraSalida = (IBarreraSalida) Naming.lookup(servidorBarreraSalida);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteCabinaCobro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteCabinaCobro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteCabinaCobro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objSirena.Reproducir();
            objBarreraSalida.Subir();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteCabinaCobro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClienteCabinaCobro";
    }
}
