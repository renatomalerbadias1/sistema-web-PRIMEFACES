package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.dao.FuncionarioDAO;

/*
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 */

public class FuncionarioController {

	public void salvar(Funcionario funcionario) {
		try {
			new FuncionarioDAO().salvar(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Funcionario> buscarPorNome(String nome) {
		ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();
		try {
			retorno = new FuncionarioDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void excluir(Funcionario funcionario) {
		try {
			new FuncionarioDAO().excluir(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Funcionario> buscarPorLogin(String login) {
		ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();
		try {
			retorno = new FuncionarioDAO().buscarPorLogin(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
}
