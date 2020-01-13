package rmi_estacionamiento;

import interfaces.IClienteSensorSalida;
import interfaces.ISirena;
import interfaces.IBarreraSalida;
import interfaces.IClienteServerCerntral;
import interfaces.IDatos;
import interfaces.IServerCerntral;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteSensorSalida extends UnicastRemoteObject implements IClienteSensorSalida, Serializable {

    IDatos objDatos;
    IClienteServerCerntral objClienteServerCerntral;
    IServerCerntral objServerCerntral;
    IBarreraSalida objBarreraSalida;
    ISirena objSirena;

    public ClienteSensorSalida() throws RemoteException {
        super();
        objDatos = null;
        objClienteServerCerntral = null;
        objServerCerntral = null;
        objBarreraSalida = null;
        objSirena = null;
    }

    @Override
    public void RecibirNotificacionEvento() throws RemoteException {
        System.out.println("El Vehiculo Salio");

        System.setSecurityManager(new SecurityManager());
        try {
            //LocateRegistry.createRegistry(1099);     
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorSalida.class.getName()).log(Level.SEVERE, null, ex);
        }
//_________________________________________________________Pedir los Proxys
        try {

            objDatos = (IDatos) Naming.lookup("rmi://localhost:1111/datos");
            objServerCerntral = (IServerCerntral) Naming.lookup("rmi://localhost:2223/serverCerntral3");
            objClienteServerCerntral = new ClienteServerCerntral(objDatos);
            Naming.rebind("rmi://localhost:1115/ClienteServerCerntral", objClienteServerCerntral);
//
            objSirena = (ISirena) Naming.lookup("rmi://localhost:1108/sirena");
            objBarreraSalida = (IBarreraSalida) Naming.lookup("rmi://localhost:1109/barreraSalida");
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteSensorSalida.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteSensorSalida.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorSalida.class.getName()).log(Level.SEVERE, null, ex);
        }
//_________________________________________________________
        try {
            objSirena.Detener();
            objBarreraSalida.Bajar();
            objServerCerntral.Solicitar(objClienteServerCerntral); // Pide entrar a Seccion Critica
            objServerCerntral.Liberar(); // Salio de la Seccion Critica
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorSalida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClienteSensorSalida";
    }
}
