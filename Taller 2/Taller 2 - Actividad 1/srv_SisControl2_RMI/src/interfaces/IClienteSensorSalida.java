/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

//____________________________________________________________

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClienteSensorSalida extends Remote {
	public void RecibirNotificacionEvento() throws RemoteException;
}
