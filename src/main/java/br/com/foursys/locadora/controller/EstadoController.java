package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.EstadoDAO;

public class EstadoController {

	public ArrayList<Estado> buscarTodos() {
		ArrayList<Estado> retorno = new ArrayList<Estado>();
		try {
			retorno = new EstadoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
