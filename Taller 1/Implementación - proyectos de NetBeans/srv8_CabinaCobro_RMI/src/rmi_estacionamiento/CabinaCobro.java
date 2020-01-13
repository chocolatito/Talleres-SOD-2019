package rmi_estacionamiento;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CabinaCobro extends UnicastRemoteObject implements ICabinaCobro {

    ArrayList<IClienteCabinaCobro> suscriptos;
    Contacto contacto;
    Notificacion hNotificacion;
    SimuladorContacto simulador;

    public CabinaCobro() throws RemoteException {
        super();
        contacto = new Contacto();
        suscriptos = new ArrayList<>();
        hNotificacion = new Notificacion(contacto, suscriptos);
    }

    @Override
    public void Suscribirse(IClienteCabinaCobro cliente) throws RemoteException {
        System.out.println("\n|_>ClienteCabinaCobro Suscripto\n");
        suscriptos.add(cliente);
        IniciarSimulador();

    }

    public void IniciarSimulador() throws RemoteException {
        simulador = new SimuladorContacto(contacto);

    }
}
