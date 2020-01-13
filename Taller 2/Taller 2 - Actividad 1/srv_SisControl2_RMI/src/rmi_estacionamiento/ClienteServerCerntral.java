package rmi_estacionamiento;

import interfaces.IDatos;
import interfaces.IClienteServerCerntral;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClienteServerCerntral extends UnicastRemoteObject implements IClienteServerCerntral, Serializable {

    IDatos datos;
    String IDProceso;

    public ClienteServerCerntral(IDatos d) throws RemoteException {
        super();
        datos = d;
        IDProceso = "PS_2";
    }
    @Override
    public String RetornarID() throws RemoteException {
        return IDProceso;
    }
    @Override
    public void IngresarSeccionCritica() throws RemoteException {
        datos.Decrementar();
    }

    @Override
    public String toString() {
        return "ClienteServerCerntral";
    }
}
