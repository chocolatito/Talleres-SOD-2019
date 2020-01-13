package rmi_estacionamiento;

import interfaces.IClienteSensorTicket;
import interfaces.IClienteSensorEntrada;
import interfaces.ISensorEntrada;
import interfaces.IClienteSensorAvance;
import interfaces.ISensorTicket;
import interfaces.ISensorAvance;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Srv_SisControl1_RMI {

    public static void main(String[] args) {
        ISensorEntrada objSensorEntrada = null;
        IClienteSensorEntrada objClienteSensorEntrada = null;
        ISensorTicket objSensorTicket = null;
        IClienteSensorTicket objClienteSensorTicket = null;
        ISensorAvance objSensorAvance = null;
        IClienteSensorAvance objClienteSensorAvance = null;
        //
        System.setSecurityManager(new SecurityManager());
        try {
            LocateRegistry.createRegistry(1100);
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(Srv_SisControl1_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        try {
            objClienteSensorEntrada = new ClienteSensorEntrada();
            Naming.rebind("rmi://localhost:1100/ClienteSensorEntrada", objClienteSensorEntrada);
            objSensorEntrada = (ISensorEntrada) Naming.lookup("rmi://localhost:1199/sensorEntrada");

            objClienteSensorTicket = new ClienteSensorTicket();
            Naming.rebind("rmi://localhost:1100/ClienteSensorTicket", objClienteSensorTicket);
            objSensorTicket = (ISensorTicket) Naming.lookup("rmi://localhost:1103/sensorTicket");

            objClienteSensorAvance = new ClienteSensorAvance();
            Naming.rebind("rmi://localhost:1100/ClienteSensorAvance", objClienteSensorAvance);
            objSensorAvance = (ISensorAvance) Naming.lookup("rmi://localhost:1106/sensorAvance");
            /**/
        } catch (RemoteException ex) {
            Logger.getLogger(Srv_SisControl1_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Srv_SisControl1_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Srv_SisControl1_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objSensorEntrada.Suscribirse(objClienteSensorEntrada);
            objSensorTicket.Suscribirse(objClienteSensorTicket);
            objSensorAvance.Suscribirse(objClienteSensorAvance);
            /**/
        } catch (RemoteException ex) {
            Logger.getLogger(Srv_SisControl1_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
