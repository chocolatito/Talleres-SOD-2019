/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDatos extends Remote {
    void Incrementar() throws RemoteException;
     void Decrementar() throws RemoteException;
     public boolean Disponible() throws RemoteException;
}