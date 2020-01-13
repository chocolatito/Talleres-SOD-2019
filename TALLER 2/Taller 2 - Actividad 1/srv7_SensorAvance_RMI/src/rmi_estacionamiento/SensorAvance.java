package rmi_estacionamiento;

import interfaces.ISensorAvance;
import interfaces.IClienteSensorAvance;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SensorAvance extends UnicastRemoteObject implements ISensorAvance {

    ArrayList<IClienteSensorAvance> suscriptos;
    Contacto contacto;
    Notificacion hNotificacion;
    SimuladorContacto simulador;

    public SensorAvance() throws RemoteException {
        super();
        contacto = new Contacto();
        suscriptos = new ArrayList<>();
        hNotificacion = new Notificacion(contacto, suscriptos);
    }

    @Override
    public void Suscribirse(IClienteSensorAvance cliente) throws RemoteException {
        System.out.println("\n|_>ClienteSensorAvance Suscripto\n");
        suscriptos.add(cliente);
        IniciarSimulador();

    }

    public void IniciarSimulador() throws RemoteException {
        simulador = new SimuladorContacto(contacto);

    }
}
