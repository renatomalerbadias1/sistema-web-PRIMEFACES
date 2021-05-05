package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.dao.LocacaoDAO;

/*
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 */

public class LocacaoController {

	public void salvar(Locacao locacao) {
		try {
			new LocacaoDAO().salvar(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Locacao> buscarDevolvido(String devolvido) {
		try {
			return new LocacaoDAO().buscarDevolvido(devolvido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
