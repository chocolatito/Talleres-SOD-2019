package rmi_estacionamiento;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Srv8_CabinaCobro_RMI {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1107);
        } catch (RemoteException ex) {
            Logger.getLogger(Srv8_CabinaCobro_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ICabinaCobro objCabinaCobro = new CabinaCobro();
            Naming.rebind("rmi://localhost:1107/cabinaCobro", objCabinaCobro);
            System.out.println("\n_>Srv8_CabinaCobro_RMI CREADO...\n");
        } catch (RemoteException ex) {
            Logger.getLogger(Srv8_CabinaCobro_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Srv8_CabinaCobro_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
