package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISensorSalida extends Remote {
	public void Suscribirse(IClienteSensorSalida cliente) throws RemoteException;
}
