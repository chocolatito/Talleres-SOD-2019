package rmi_estacionamiento;

import interfaces.IClienteSensorTicket;
import interfaces.ISensorTicket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SensorTicket extends UnicastRemoteObject implements ISensorTicket {

    ArrayList<IClienteSensorTicket> suscriptos;
    Contacto contacto;
    Notificacion hNotificacion;
    SimuladorContacto simulador;

    public SensorTicket() throws RemoteException {
        super();
        contacto = new Contacto();
        suscriptos = new ArrayList<>();
        hNotificacion = new Notificacion(contacto, suscriptos);
    }

    @Override
    public void Suscribirse(IClienteSensorTicket cliente) throws RemoteException {
        System.out.println("\n|_>ClienteSensorTicket Suscripto\n");
        suscriptos.add(cliente);
        IniciarSimulador();

    }

    public void IniciarSimulador() throws RemoteException {
        simulador = new SimuladorContacto(contacto);

    }
}
