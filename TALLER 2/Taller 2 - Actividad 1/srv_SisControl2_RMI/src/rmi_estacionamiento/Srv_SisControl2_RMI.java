package rmi_estacionamiento;

import interfaces.IClienteSensorSalida;
import interfaces.ICabinaCobro;
import interfaces.ISensorSalida;
import interfaces.IClienteCabinaCobro;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Srv_SisControl2_RMI {

    public static void main(String[] args) {
        ICabinaCobro objCabinaCobro = null;
        IClienteCabinaCobro objClienteCabinaCobro = null;
        ISensorSalida objSensorSalida = null;
        IClienteSensorSalida objClienteSensorSalida = null;
        //
        System.setSecurityManager(new SecurityManager());
        try {
            LocateRegistry.createRegistry(1115);
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(Srv_SisControl2_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        try {
            objClienteCabinaCobro = new ClienteCabinaCobro();
            Naming.rebind("rmi://localhost:1115/ClienteCabinaCobro", objClienteCabinaCobro);
            objCabinaCobro = (ICabinaCobro) Naming.lookup("rmi://localhost:1107/cabinaCobro");

            objClienteSensorSalida = new ClienteSensorSalida();
            Naming.rebind("rmi://localhost:1115/ClienteSensorSalida", objClienteSensorSalida);
            objSensorSalida = (ISensorSalida) Naming.lookup("rmi://localhost:1110/sensorSalida");
        } catch (RemoteException ex) {
            Logger.getLogger(Srv_SisControl2_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Srv_SisControl2_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Srv_SisControl2_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objCabinaCobro.Suscribirse(objClienteCabinaCobro);
            objSensorSalida.Suscribirse(objClienteSensorSalida);
        } catch (RemoteException ex) {
            Logger.getLogger(Srv_SisControl2_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
