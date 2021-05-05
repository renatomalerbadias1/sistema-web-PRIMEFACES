package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.dao.ContatoDAO;

/*
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 */

public class ContatoController {

	public void salvar(Contato contato) {
		try {
			new ContatoDAO().salvar(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(Contato contato) {
		try {
			new ContatoDAO().excluir(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
