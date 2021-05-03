package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.CidadeDAO;

public class CidadeController {

	public ArrayList<Cidade> buscarPorEstado(Estado estado) {
		ArrayList<Cidade> retorno = new ArrayList<Cidade>();
		try {
			retorno = new CidadeDAO().buscarPorEstado(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public ArrayList<Cidade> buscarTodos() {
		ArrayList<Cidade> retorno = new ArrayList<Cidade>();
		try {
			retorno = new CidadeDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
}
