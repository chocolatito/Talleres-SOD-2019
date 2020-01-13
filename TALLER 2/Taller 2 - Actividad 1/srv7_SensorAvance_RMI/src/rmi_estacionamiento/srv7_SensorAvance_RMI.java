package rmi_estacionamiento;

import interfaces.ISensorAvance;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class srv7_SensorAvance_RMI {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1106);
        } catch (RemoteException ex) {
            Logger.getLogger(srv7_SensorAvance_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ISensorAvance objSensorTicket = new SensorAvance();
            Naming.rebind("rmi://localhost:1106/sensorAvance", objSensorTicket);
           System.out.println("\n_>RMI_5_Srv_SensorAvance CREADO...\n");
        } catch (RemoteException ex) {
            Logger.getLogger(srv7_SensorAvance_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(srv7_SensorAvance_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
