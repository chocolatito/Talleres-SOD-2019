/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISirena extends Remote {

    public void Reproducir() throws RemoteException;

    public void Detener() throws RemoteException;
}