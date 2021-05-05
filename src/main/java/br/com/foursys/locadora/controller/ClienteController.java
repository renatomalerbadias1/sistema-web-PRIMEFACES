package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.dao.ClienteDAO;

/*
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 */

public class ClienteController {

	public void salvar(Cliente cliente) {
		try {
			new ClienteDAO().salvar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> buscarPorNome(String nome) {
		ArrayList<Cliente> retorno = new ArrayList<Cliente>();
		try {
			retorno = new ClienteDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void excluir(Cliente cliente) {
		try {
			new ClienteDAO().excluir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cliente> buscarTodos() {
		ArrayList<Cliente> retorno = new ArrayList<Cliente>();
		try {
			retorno = new ClienteDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
}
