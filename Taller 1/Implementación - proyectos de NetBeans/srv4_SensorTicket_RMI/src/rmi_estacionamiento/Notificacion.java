package rmi_estacionamiento;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Notificacion extends Thread {

    Contacto contacto;
    ArrayList<IClienteSensorTicket> suscriptos;

    public Notificacion(Contacto c, ArrayList<IClienteSensorTicket> s) {
        contacto = c;
        suscriptos = s;
        start();
    }

    @Override
    public void run() {
        while (true) {
            contacto.EsperarContacto();
            for (int i = 0; i < suscriptos.size(); i++) {
                try {
                    suscriptos.get(i).RecibirNotificacionEvento();
                } catch (RemoteException ex) {
                    Logger.getLogger(Notificacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
