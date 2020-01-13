package rmi_estacionamiento;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteSensorEntrada extends UnicastRemoteObject implements IClienteSensorEntrada, Serializable {

    public ClienteSensorEntrada() throws RemoteException {
        super();
    }

    @Override
    public void RecibirNotificacionEvento() throws RemoteException {
        System.out.println("Ingreso un automovil");

        IParlante objParlante = null;
        IImpresora objImpresora = null;
        System.setSecurityManager(new SecurityManager());
        try {
            //LocateRegistry.createRegistry(1099);     
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String servidorParlante = "rmi://localhost:1102/parlante";
            objParlante = (IParlante) Naming.lookup(servidorParlante);
            String servidorImpresora = "rmi://localhost:1101/impresora";
            objImpresora = (IImpresora) Naming.lookup(servidorImpresora);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objParlante.EstablecerMensaje("Bienvenido estimado cliente!");
            objParlante.TransmitirMensaje();
            objImpresora.EstablecerTicket("__________________\n"
                    + "  Fecha  xx:xx:xxxx\n"
                    + "  Hora     yy:yy\n"
                    + "  Codigo  zzzzzzzzz\n"
                    + "__________________");
            objImpresora.EmitirTicket();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClienteSensorEntrada";
    }
}
