package rmi_estacionamiento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISensorTicket extends Remote {
	public void Suscribirse(IClienteSensorTicket cliente) throws RemoteException;
}
