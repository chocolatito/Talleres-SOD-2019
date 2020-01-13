package rmi_estacionamiento;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class srv1_SensorEntrada_RMI {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            Logger.getLogger(srv1_SensorEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ISensorEntrada objSensorEntrada = new SensorEntrada();
            Naming.rebind("rmi://localhost:1099/sensorEntrada", objSensorEntrada);
            System.out.println("\n_>RMI_1_Srv_SensorEntrada CREADO...\n");
        } catch (RemoteException ex) {
            Logger.getLogger(srv1_SensorEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(srv1_SensorEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
