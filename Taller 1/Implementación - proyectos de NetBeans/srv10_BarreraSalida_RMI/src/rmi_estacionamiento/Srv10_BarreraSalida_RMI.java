package rmi_estacionamiento;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Srv10_BarreraSalida_RMI {

    public static void main(String[] args) {
        try {
            //           LocateRegistry.getRegistry();
            LocateRegistry.createRegistry(1109);
        } catch (RemoteException ex) {
            Logger.getLogger(Srv10_BarreraSalida_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            LocateRegistry.getRegistry(1109);
        } catch (RemoteException ex) {
            Logger.getLogger(Srv10_BarreraSalida_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            IBarreraSalida objBarreraSalida = new BarreraSalida();
            Naming.rebind("rmi://localhost:1109/barreraSalida", objBarreraSalida);
            System.out.println("Srv10_BarreraSalida_RMI Disponible");
        } catch (RemoteException ex) {
            Logger.getLogger(Srv10_BarreraSalida_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Srv10_BarreraSalida_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
