package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.dao.FormaPagamentoDAO;

/*
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 */

public class FormaPagamentoController {

	public ArrayList<FormaPagamento> buscarTodos() {
		ArrayList<FormaPagamento> retorno = new ArrayList<FormaPagamento>();
		try {
			retorno = new FormaPagamentoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
