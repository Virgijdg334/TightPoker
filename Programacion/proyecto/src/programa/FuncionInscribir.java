package programa;

import javax.swing.*;
import java.awt.*;

public class FuncionInscribir extends JFrame{	
	
	public static boolean Inscribir(int precio, int jugadores, int maximo) {
		
		Usuario actual = SesionUsuario.getUsuario();
		
		double saldo = actual.getSaldo();

		if (saldo >= precio){
			
			JOptionPane.showMessageDialog(null, "Te has inscrito correctamente a el torneo.");
			saldo = saldo-precio;
			return true;
			
		} else if(jugadores==maximo){
			
			JOptionPane.showMessageDialog(null, "No quedan espacios disponibles en el torneo");
			
			return false;
		}else {
			
			JOptionPane.showMessageDialog(null, "No tienes saldo suficiente");
		
			return false;
		}
	}
	
	
}
