/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_estacionamiento;

import interfaces.ISemaforoEntrada;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class srv5_SemaforoEntrada_RMI {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(1104);
        } catch (RemoteException ex) {
            try {
            LocateRegistry.getRegistry(1104);
            } catch (RemoteException ex1) {
                Logger.getLogger(srv5_SemaforoEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(srv5_SemaforoEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ISemaforoEntrada objSemaforoEntrada = new SemaforoEntrada();
            Naming.rebind("rmi://localhost:1104/semaforoEntrada", objSemaforoEntrada);
            System.out.println("RMI_4_Srv_SemaforoEntrada Disponible");
        } catch (RemoteException ex) {
            Logger.getLogger(srv5_SemaforoEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(srv5_SemaforoEntrada_RMI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
