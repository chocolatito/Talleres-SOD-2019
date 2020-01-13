package rmi_estacionamiento;

import interfaces.IServidorDF;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetectorFallos extends UnicastRemoteObject implements IServidorDF {

    boolean[] procesos;
    int idProceso, idElegido, cantidadProcesos, cantCaidas;
    boolean participante;
    Timer timer;//DF, timerDF1, timerEleccion;
    TimerTask taskPasarMensaje, taskControlarFallos, taskEsperaEleccion, taskPrimeraEleccion;
    DateFormat hourFormat;

    public DetectorFallos(int cantPs, int idPs) throws RemoteException {
        super();
        cantidadProcesos = cantPs;
        procesos = new boolean[cantPs];
        idProceso = idPs;
        idElegido = idPs;
        cantCaidas = 0;
        participante = false;
        hourFormat = new SimpleDateFormat("HH:mm:ss:SS");//Date()        
        IniciarSaludo();
    }
//________________________________________________________________________________________________
//________________________________________________________________________________________________

//________________________________________________________________________________________________
    // TIMERS para Control de Fallos
    public void IniciarSaludo() {
        timer = new Timer();
        taskPasarMensaje = new TimerTask() {
            @Override
            public void run() {
                PasarMensaje();
            }
        };
        taskPrimeraEleccion = new TimerTask() {
            @Override
            public void run() {;
                timer.cancel();
                participante = true;
                EnviarEleccion(idProceso);
            }
        };
        timer.schedule(taskPasarMensaje, 0, 1000);
        timer.schedule(taskPrimeraEleccion, 6000);
    }

    public void IniciarControlFallos() {
        timer.cancel();
        timer = new Timer();
        taskPasarMensaje = new TimerTask() {
            @Override
            public void run() {
                PasarMensaje();
            }
        };
        taskControlarFallos = new TimerTask() {
            @Override
            public void run() {
                ControlarFallos();
            }
        };
        timer.schedule(taskPasarMensaje, 0, 2000);
        timer.schedule(taskControlarFallos, 3000, 5000);
    }

    public void DetenerControlFallos() {
        timer.cancel();
    }
    //_____________________________________________________________________________________________

    //_____________________________________________________________________________________________
    // TIMERS para Elecciones
    public void IniciarEsperaElegido() {
        timer = new Timer();
        taskEsperaEleccion = new TimerTask() {
            @Override
            public void run() {
                participante = false;
                System.out.println(hourFormat.format(new Date()) + "\n_ > Se ejecuta la tarea taskEsperaEleccion\n");
                FinalizarEleccion();
            }
        };
        timer.schedule(taskEsperaEleccion, 7000);
        System.out.println(hourFormat.format(new Date()) + "\n_ > public void IniciarEsperaElegido()");
    }

    public void DetenerEsperaElegido() {
        timer.cancel();
    }
//________________________________________________________________________________________________
//________________________________________________________________________________________________

//________________________________________________________________________________________________
    // Elecciones
    public void EnviarEleccion(int idPs) {
        //____________________________________________________________
        System.out.println("\n| " + hourFormat.format(new Date()) + " | >>>El proceso PS" + idPs + " solicita eleccion\n");
        int puerto = 9999;
        int vecinoActivo = 9999;
        boolean hayActivos = false;
        for (int i = 0; i < cantidadProcesos; ++i) {
            vecinoActivo = (idProceso + i) % cantidadProcesos;
            if (procesos[vecinoActivo]) {
                puerto = 2100 + vecinoActivo;
                hayActivos = true;
                break;
            }
        }
        //:__________________________________________________________
        if (hayActivos) {
            try {
                IServidorDF procesoVecinoActivo = (IServidorDF) Naming.lookup("rmi://localhost:" + puerto + "/servidorDF" + vecinoActivo);
                IniciarEsperaElegido();
                procesoVecinoActivo.RecibirEleccion(idPs);
            } catch (NotBoundException ex) {
            } catch (MalformedURLException ex) {
            } catch (RemoteException ex) {
            }

        } else {
            System.out.println(hourFormat.format(new Date()) + "\n\tNo hay vecinos activos....\n");
            idElegido = idProceso;
            participante = false;
            FinalizarEleccion();
        };
    }

    @Override
    public void RecibirEleccion(int idPs) throws RemoteException {
        if (!participante) {
            DetenerControlFallos();
            participante = true;
        }
        if (idPs == idProceso) {
            EnviarElegido(idProceso);
        } else if (idProceso < idPs) {
            idElegido = idPs;
            EnviarEleccion(idPs);
        } else {
            idElegido = idProceso;
            EnviarEleccion(idProceso);
        }
    }

    public void EnviarElegido(int idPs) {
        idElegido = idPs;
        participante = false;
        int puerto = 9999;
        int vecinoActivo = 9999;
        boolean hayActivos = false;
        for (int i = 0; i < cantidadProcesos; ++i) {
            vecinoActivo = (idProceso + i) % cantidadProcesos;
            if (procesos[vecinoActivo]) {
                puerto = 2100 + vecinoActivo;
                hayActivos = true;
                break;
            }
        }
        //:__________________________________________________________
        if (hayActivos) {
            try {
                IServidorDF procesoVecinoActivo = (IServidorDF) Naming.lookup("rmi://localhost:" + puerto + "/servidorDF" + vecinoActivo);
                procesoVecinoActivo.RecibirElegido(idPs);
            } catch (NotBoundException ex) {
            } catch (MalformedURLException ex) {
            } catch (RemoteException ex) {
            }
        }
        FinalizarEleccion();
    }

    @Override
    public void RecibirElegido(int idPs) throws RemoteException {
        if (participante) {
            participante = false;
        }
        if (idPs != idProceso) {
            EnviarElegido(idPs);
        } else {
            System.out.println("\t|  " +hourFormat.format(new Date()) + "\n\t| Elegido PS" + idElegido + " recorrio el anillo\n");
        }
        FinalizarEleccion();
    }

    public void FinalizarEleccion() {
        if (participante) {
            timer.cancel();
        }
        System.out.println(hourFormat.format(new Date()) + "\n  | - > ELECCION FINALIZADA....\n\tEl elegido es PS" + idElegido + ""
                + "\n\n________________________________________________________________________________________________\n");

        IniciarControlFallos();
    }

//________________________________________________________________________________________________
//________________________________________________________________________________________________
//________________________________________________________________________________________________
    // Controlar Fallos
    public void ControlarFallos() {
        if (idProceso == idElegido) {
            System.out.println(hourFormat.format(new Date()) + "\n|- > ELEGIDO PS" + idElegido + " Activo");
        } else if (procesos[idElegido]) {
            cantCaidas = 0;
            procesos[idElegido] = false;
            System.out.println(hourFormat.format(new Date()) + "\n|- > ELEGIDO PS" + idElegido + " Activo");
        } else {
            ++cantCaidas;
            System.out.println(hourFormat.format(new Date()) + "\n|- > ELEGIDO PS" + idElegido + " tiene " + cantCaidas + " caidas");
        }
        if (cantCaidas == 3) {
            DetenerControlFallos();
            cantCaidas = 0;
            participante = true;
            idElegido = idProceso;
            EnviarEleccion(idProceso);
        } else {
            for (int i = 0; i < cantidadProcesos; ++i) {
                if (i != idProceso && i != idElegido) {
                    if (procesos[i]) {
                        procesos[i] = false;
                    } else {
                        System.out.println("| "+hourFormat.format(new Date()) + " | - >El proceso PS" + i + " esta potencialmente caido. < - |");
                    }
                }
            }
            System.out.println("________________________________________________________________________________________________\n");
        }
    }

    //_________________________________
    // Notificar >Proceso Activo< a los demas
    public void PasarMensaje() {
        for (int i = 1; i < cantidadProcesos; ++i) {
            int sigProceso = (idProceso + i) % cantidadProcesos;
            int puerto = 2100 + sigProceso;
            try {
                IServidorDF procesoVecino = (IServidorDF) Naming.lookup("rmi://localhost:" + puerto + "/servidorDF" + sigProceso);
                procesoVecino.RecibirMensaje(idProceso);
            } catch (NotBoundException ex) {
            } catch (MalformedURLException ex) {
            } catch (RemoteException ex) {
            }
        }
    }

    @Override
    public void RecibirMensaje(int idPs) throws RemoteException {
        procesos[idPs] = true;
    }
//________________________________________________________________________________________________
//________________________________________________________________________________________________
}
