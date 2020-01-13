/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_estacionamiento;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class srv2_Impresora_RMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //           LocateRegistry.getRegistry();
            LocateRegistry.createRegistry(1101);
        } catch (RemoteException ex) {
            Logger.getLogger(srv2_Impresora_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            IImpresora objParlante = new Impresora();
            Naming.rebind("rmi://localhost:1101/impresora", objParlante);
            System.out.println("RMI_2_Srv_Impresora Disponible");
        } catch (RemoteException ex) {
            Logger.getLogger(srv2_Impresora_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(srv2_Impresora_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
