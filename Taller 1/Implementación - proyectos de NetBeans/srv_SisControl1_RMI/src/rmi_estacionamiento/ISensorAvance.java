package rmi_estacionamiento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISensorAvance extends Remote {
	public void Suscribirse(IClienteSensorAvance cliente) throws RemoteException;
}
