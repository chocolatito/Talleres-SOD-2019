
package rmi_estacionamiento;

import java.util.logging.Level;
import java.util.logging.Logger;

class Contacto{
    boolean hayContacto;
    public Contacto(){
        hayContacto = false;
        
    }
    public synchronized void EsperarContacto(){
        if (!hayContacto){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Contacto.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
        hayContacto = false;
        notifyAll();
    }
    	
	public synchronized void NotificarContacto(){
		if (hayContacto){
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Contacto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                hayContacto = true;
                notifyAll();
        }
}