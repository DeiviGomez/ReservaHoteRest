package com.presentacion.rest;


import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import AccesoDatos.HabitacionDL;
import AccesoDatos.UsuarioDL;
import Entidades.DetalleReservaEL;
import Entidades.HabitacionEL;
import Entidades.UsuarioEL;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
				
		return "home";
	}
	
	@RequestMapping(value = "/restlistarHabDisponibles", method = RequestMethod.GET, 
			produces="application/JSON")	
	public @ResponseBody List<HabitacionEL> ListaHabitacionesDisponibles(String diaEntrada, String diaSalida) {
		List<HabitacionEL> c = null;
		try {
						
				c=HabitacionDL.instancia().sp_Buscar_Habitaciones_disponibles(diaEntrada, diaSalida);					
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	

	@RequestMapping(value = "/restlistarHabOcupadas", method = RequestMethod.GET, 
			produces="application/JSON")	
	public @ResponseBody List<DetalleReservaEL> ListaHabitacionesOcupadas(String diaEntrada, String diaSalida) {
		List<DetalleReservaEL> c = null;
		try {
			c=HabitacionDL.instancia().sp_Buscar_Habitaciones_Ocupadas(diaEntrada, diaSalida);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
	@RequestMapping(value = "/restverificaacceso", method = RequestMethod.GET, 
			produces="application/JSON")	
	public @ResponseBody int VerificarAcceso(String _Usuario, String _Pass) {
		UsuarioEL us = null;
		int u = 0;
		try {
			us = UsuarioDL.instancia().VerificarAcceso_Rest(_Usuario, _Pass);
			u=Integer.parseInt(us.getTipoUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	
}
