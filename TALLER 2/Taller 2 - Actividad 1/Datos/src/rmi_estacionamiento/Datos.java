package rmi_estacionamiento;

import interfaces.IDatos;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.server.UnicastRemoteObject;

public class Datos extends UnicastRemoteObject implements IDatos {

    int Max;                    // constante
    int CantVehiculos; // variable contador, podria ser una lista de vehiculos.

    public Datos() throws RemoteException {
        super();
        Max = 20;
        CantVehiculos = 0;
    }

    @Override
    public void Incrementar() throws RemoteException {
        CantVehiculos = CantVehiculos + 1;
        System.out.print("La cantidad actual es ");
        System.out.println(CantVehiculos);
    }

    @Override
    public void Decrementar() throws RemoteException {
        CantVehiculos = CantVehiculos - 1;
        System.out.print("La cantidad actual es ");
        System.out.println(CantVehiculos);
    }

    @Override
    public boolean Disponible() throws RemoteException {
        return CantVehiculos < Max;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1111);
        } catch (RemoteException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            IDatos objDatos = new Datos();
            Naming.rebind("rmi://localhost:1111/datos", objDatos);
            System.out.println("\n_>Los Datos del Estacionamiento Estan Disponibles...\n");
        } catch (RemoteException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
