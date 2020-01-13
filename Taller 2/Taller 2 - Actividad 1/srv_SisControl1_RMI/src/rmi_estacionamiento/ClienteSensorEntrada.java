package rmi_estacionamiento;

import interfaces.IClienteSensorEntrada;
import interfaces.IDatos;
import interfaces.IImpresora;
import interfaces.IParlante;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteSensorEntrada extends UnicastRemoteObject implements IClienteSensorEntrada, Serializable {

    //        
    IDatos objDatos;
    IParlante objParlante;
    IImpresora objImpresora;

    public ClienteSensorEntrada() throws RemoteException {
        super();
        objDatos = null;
        objParlante = null;
        objImpresora = null;
    }

    @Override
    public void RecibirNotificacionEvento() throws RemoteException {
        System.out.println("\n|_ -  > Ingreso un automovil\n...\n...");

        System.setSecurityManager(new SecurityManager());
        try {
            //LocateRegistry.createRegistry(1099);     
            LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        try {
            objDatos = (IDatos) Naming.lookup("rmi://localhost:1111/datos");
            objParlante = (IParlante) Naming.lookup("rmi://localhost:1102/parlante");
            objImpresora = (IImpresora) Naming.lookup("rmi://localhost:1101/impresora");
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Pregunto Si Hay Lugar En El Estacionamiento
            if (objDatos.Disponible()) {
                objParlante.EstablecerMensaje("Bienvenido estimado cliente!\n Por Favor Retire El Ticket...");
                objImpresora.EstablecerTicket(" __________________\n"
                        + "  Fecha  xx:xx:xxxx\n"
                        + "  Hora     yy:yy\n"
                        + "  Codigo  zzzzzzzzz\n"
                        + "|__________________|");
                objImpresora.EmitirTicket();
            } else {
                objParlante.EstablecerMensaje("|______________________________________________|"
                        + " Lo Sentimo NO HAY LUGAR DISPONIBLE POR AHORA!"
                        + "|______________________________________________|");
            }
            objParlante.TransmitirMensaje();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteSensorEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClienteSensorEntrada";
    }
}
