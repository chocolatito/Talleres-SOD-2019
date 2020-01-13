package rmi_estacionamiento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinaCobro extends Remote {
	public void Suscribirse(IClienteCabinaCobro cliente) throws RemoteException;
}
