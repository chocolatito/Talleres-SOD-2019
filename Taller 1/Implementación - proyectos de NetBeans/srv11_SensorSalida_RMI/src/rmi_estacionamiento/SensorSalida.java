package rmi_estacionamiento;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SensorSalida extends UnicastRemoteObject implements ISensorSalida {

    ArrayList<IClienteSensorSalida> suscriptos;
    Contacto contacto;
    Notificacion hNotificacion;
    SimuladorContacto simulador;

    public SensorSalida() throws RemoteException {
        super();
        contacto = new Contacto();
        suscriptos = new ArrayList<>();
        hNotificacion = new Notificacion(contacto, suscriptos);
    }

    @Override
    public void Suscribirse(IClienteSensorSalida cliente) throws RemoteException {
        System.out.println("\n|_>ClienteSensorSalida Suscripto\n");
        suscriptos.add(cliente);
        IniciarSimulador();

    }

    public void IniciarSimulador() throws RemoteException {
        simulador = new SimuladorContacto(contacto);

    }
}
