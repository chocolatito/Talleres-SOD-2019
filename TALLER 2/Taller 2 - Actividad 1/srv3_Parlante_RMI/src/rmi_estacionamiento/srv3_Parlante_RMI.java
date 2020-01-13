package rmi_estacionamiento;

import interfaces.IParlante;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class srv3_Parlante_RMI {

    public static void main(String[] args) {
        try {
 //           LocateRegistry.getRegistry();
          LocateRegistry.createRegistry(1102);
        } catch (RemoteException ex) {
            Logger.getLogger(srv3_Parlante_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            IParlante objParlante = new Parlante();
            Naming.rebind("rmi://localhost:1102/parlante", objParlante);
            System.out.println("RMI_2_Srv_Parlante Disponible");
        } catch (RemoteException ex) {
            Logger.getLogger(srv3_Parlante_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(srv3_Parlante_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
