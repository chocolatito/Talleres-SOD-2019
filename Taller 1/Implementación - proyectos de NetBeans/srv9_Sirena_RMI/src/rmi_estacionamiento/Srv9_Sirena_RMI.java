package rmi_estacionamiento;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Srv9_Sirena_RMI {
    
    public static void main(String[] args) {
        try {
            //           LocateRegistry.getRegistry();
            LocateRegistry.createRegistry(1108);
        } catch (RemoteException ex) {
            Logger.getLogger(Srv9_Sirena_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            LocateRegistry.getRegistry(1108);
        } catch (RemoteException ex) {
            Logger.getLogger(Srv9_Sirena_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ISirena objSirena = new Sirena();
            Naming.rebind("rmi://localhost:1108/sirena", objSirena);
            System.out.println("Srv9_Sirena_RMI Disponible");
        } catch (RemoteException ex) {
            Logger.getLogger(Srv9_Sirena_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Srv9_Sirena_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
