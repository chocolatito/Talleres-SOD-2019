package rmi_estacionamiento;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SensorEntrada extends UnicastRemoteObject implements ISensorEntrada {

    ArrayList<IClienteSensorEntrada> suscriptos;
    Contacto contacto;
    Notificacion hNotificacion;
    SimuladorContacto simulador;

    public SensorEntrada() throws RemoteException {
        super();
        contacto = new Contacto();
        suscriptos = new ArrayList<>();
        hNotificacion = new Notificacion(contacto, suscriptos);
    }

    @Override
    public void Suscribirse(IClienteSensorEntrada cliente) throws RemoteException {
        System.out.println("\n|_>ClienteSensorEntrada Suscripto\n");
        suscriptos.add(cliente);
        IniciarSimulador();

    }

    public void IniciarSimulador() throws RemoteException {
        simulador = new SimuladorContacto(contacto);

    }
}
