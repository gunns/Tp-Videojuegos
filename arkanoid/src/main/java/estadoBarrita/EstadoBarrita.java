package estadoBarrita;

import com.uqbar.arkanoid.ArkanoidScene;
import com.uqbar.arkanoid.Barrita;

public abstract class EstadoBarrita {
	
	private int cantidadDeRebotes;
	//es estado de la barrita depende del bonus que hayas agarrado con la barrita
	//este dura una cantidad de rebotes que definiremos como específico en cada estado
	
	
	abstract void aplicarEfecto(Barrita b);
	

}
