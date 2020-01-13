
package rmi_estacionamiento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISensorEntrada extends Remote {
	public void Suscribirse(IClienteSensorEntrada cliente) throws RemoteException;
}