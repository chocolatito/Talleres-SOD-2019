/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_estacionamiento;

//____________________________________________________________

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClienteCabinaCobro extends Remote {
	public void RecibirNotificacionEvento() throws RemoteException;
}
