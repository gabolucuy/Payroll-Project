package views;

import updatable.Updatable;

public class EmpleadoView implements Updatable {

	public String updateNombre(String nombre) {
		return "<div>"
				+ "<p>Nombre: </p>"
				+ "<p>"+ nombre +"</p>"
				+"</div>";
	}

}
