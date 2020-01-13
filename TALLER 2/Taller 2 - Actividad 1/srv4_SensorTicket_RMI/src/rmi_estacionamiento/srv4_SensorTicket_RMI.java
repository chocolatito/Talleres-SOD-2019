package rmi_estacionamiento;

import interfaces.ISensorTicket;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class srv4_SensorTicket_RMI {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1103);
        } catch (RemoteException ex) {
            Logger.getLogger(srv4_SensorTicket_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ISensorTicket objSensorTicket = new SensorTicket();
            Naming.rebind("rmi://localhost:1103/sensorTicket", objSensorTicket);
           System.out.println("\n_>RMI_3_Srv_SensorTicket CREADO..\n");
        } catch (RemoteException ex) {
            Logger.getLogger(srv4_SensorTicket_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(srv4_SensorTicket_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
