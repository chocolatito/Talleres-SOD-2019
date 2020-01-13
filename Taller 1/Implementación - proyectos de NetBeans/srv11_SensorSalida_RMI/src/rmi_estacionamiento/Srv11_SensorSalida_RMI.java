package rmi_estacionamiento;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Srv11_SensorSalida_RMI {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1110);
        } catch (RemoteException ex) {
            Logger.getLogger(Srv11_SensorSalida_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ISensorSalida objSensorSalida = new SensorSalida();
            Naming.rebind("rmi://localhost:1110/sensorSalida", objSensorSalida);
            System.out.println("\n_>Srv11_SensorSalida_RMI CREADO...\n");
        } catch (RemoteException ex) {
            Logger.getLogger(Srv11_SensorSalida_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Srv11_SensorSalida_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}