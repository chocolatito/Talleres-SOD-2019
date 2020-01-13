package rmi_estacionamiento;

import interfaces.IClienteServerCerntral;
import interfaces.IServerCerntral;
import interfaces.IServidorDF;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerCerntralDF extends UnicastRemoteObject implements IServerCerntral {

    //  true -> disponnible; false-> ha sido concedido
    boolean testigo;
    ArrayList<IClienteServerCerntral> solicitudes;
    Date fecha;
    String IDProceso;

    public ServerCerntralDF() throws RemoteException {
        super();
        testigo = true;
        solicitudes = new ArrayList<>();
        fecha = new Date();
        IDProceso = "";
    }

    @Override
    public void Solicitar(IClienteServerCerntral cliente) throws RemoteException {
        solicitudes.add(cliente);         // añadido a la cola
        Conceder();                     // Este metodo no debe ser remoto
    }

    public void Conceder() {
        if (solicitudes.size() > 0 && testigo) {
            testigo = false;
            IClienteServerCerntral cliente = solicitudes.remove(0);
            try {
                IDProceso = cliente.RetornarID();
                cliente.IngresarSeccionCritica(); // ahora puede ingresar a SC
                System.out.print(fecha);
                System.out.println(" Se concedio el Testigo al proceso " + IDProceso);
            } catch (RemoteException ex) {
                Logger.getLogger(ServerCerntralDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Liberar() throws RemoteException {
        testigo = true;
        System.out.print(fecha);
        System.out.println(" Se libero el Testigo por el proceso " + IDProceso + "\n");
        Conceder();
    }

//__________________________________
    public static void main(String[] args) {
        int i = 2;
                try {
            LocateRegistry.createRegistry(2100 + i);
            IServidorDF server = new DetectorFallos(4, i);
            Naming.rebind("rmi://localhost:210" + i + "/servidorDF" + i, server);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerCerntralDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerCerntralDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        IServerCerntral objSCentral = null;
        try {
            LocateRegistry.createRegistry(2220 + i);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerCerntralDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objSCentral = new ServerCerntralDF();
            Naming.rebind("rmi://localhost:222" + i + "/serverCerntral" + i, objSCentral);
            System.out.println("\n_>Servidor Central Ejecutando.\n");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerCerntralDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerCerntralDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
