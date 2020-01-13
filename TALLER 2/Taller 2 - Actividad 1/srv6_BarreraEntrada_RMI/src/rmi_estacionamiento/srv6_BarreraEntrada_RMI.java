package rmi_estacionamiento;

import interfaces.IBarreraEntrada;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class srv6_BarreraEntrada_RMI {

    public static void main(String[] args) {
        try {
            //           LocateRegistry.getRegistry();
            LocateRegistry.createRegistry(1105);
        } catch (RemoteException ex) {
            Logger.getLogger(srv6_BarreraEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            LocateRegistry.getRegistry(1105);
        } catch (RemoteException ex) {
            Logger.getLogger(srv6_BarreraEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            IBarreraEntrada objBarreraEntrada = new BarreraEntrada();
            Naming.rebind("rmi://localhost:1105/barreraEntrada", objBarreraEntrada);
            System.out.println("RMI_4_Srv_BarreraEntrada Disponible");
        } catch (RemoteException ex) {
            Logger.getLogger(srv6_BarreraEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(srv6_BarreraEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
