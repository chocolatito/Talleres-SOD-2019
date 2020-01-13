package rmi_estacionamiento;

import interfaces.ISemaforoEntrada;
import interfaces.IBarreraEntrada;
import interfaces.IClienteSensorAvance;
import interfaces.IServerCerntral;
import interfaces.IClienteServerCerntral;
import interfaces.IDatos;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteSensorAvance extends UnicastRemoteObject implements IClienteSensorAvance, Serializable {

    IDatos ObjDatos;
    IServerCerntral ObjServerCerntral;
    IClienteServerCerntral ObjClienteServerCerntral;
    IBarreraEntrada objBarreraEntrada;
    ISemaforoEntrada objSemaforoEntrada;

    public ClienteSensorAvance() throws RemoteException {

        System.setSecurityManager(new SecurityManager());
        try {
            //LocateRegistry.createRegistry(1099);     
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorAvance.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ObjDatos = (IDatos) Naming.lookup("rmi://localhost:1111/datos");
            ObjClienteServerCerntral = new ClienteServerCerntral(ObjDatos);
            Naming.rebind("rmi://localhost:1100/ClienteServerCerntral", ObjClienteServerCerntral);
            ObjServerCerntral = (IServerCerntral) Naming.lookup("rmi://localhost:2223/serverCerntral3");

            objSemaforoEntrada = (ISemaforoEntrada) Naming.lookup("rmi://localhost:1104/semaforoEntrada");
            objBarreraEntrada = (IBarreraEntrada) Naming.lookup("rmi://localhost:1105/barreraEntrada");
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteSensorAvance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteSensorAvance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorAvance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void RecibirNotificacionEvento() throws RemoteException {
        System.out.println("El Vehiculo Avanzo");
        try {
            ObjServerCerntral.Solicitar(ObjClienteServerCerntral);
            ObjServerCerntral.Liberar();
            // if controlar si queda lugar
            objSemaforoEntrada.EncenderVerde();
            objBarreraEntrada.Bajar();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorAvance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClienteSensorAvance";
    }
}
