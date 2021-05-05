package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Valida;

/*
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 * 
 */

@ManagedBean(name = "loginBacking")
@SessionScoped
public class LoginBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;

	public boolean user;
	public boolean dev;

	public static Funcionario funcionarioLogado;

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public boolean isDev() {
		return dev;
	}

	public void setDev(boolean dev) {
		this.dev = dev;
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void efetuarLogin() {
		if (validar()) {
			try {
				ArrayList<Funcionario> funcionarios = new FuncionarioController().buscarPorLogin(login);
				if (validaDados(funcionarios)) {
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				JSFUtil.addErrorMessage("Login", "Credenciais inválidas!");
			}
		}
	}

	private boolean validar() {
		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage("Login", "Credenciais inválidas!");
			return false;
		}
		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage("Login", "Credenciais inválidas!");
			return false;
		}
		return true;
	}

	private boolean validaDados(ArrayList<Funcionario> func) {
		
		for (Funcionario funcionario : func) {
			
			if (funcionario.getSenha().equals(senha)) {
				setLogin(null);
				setSenha(null);
				setFuncionarioLogado(funcionario);
				if (funcionario.getPerfilAcesso().equals(Perfil.USER.getDescricao())) {
					setUser(true);
				} else {
					setUser(false);
					if (funcionario.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
						setDev(true);
					} else {
						setDev(false);
					}
				}
				return true;
			}
		}
		return false;
	}

}
