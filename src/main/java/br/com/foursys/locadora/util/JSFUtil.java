package br.com.foursys.locadora.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe para exibir mensagens
 * 
 * @author Diego Munhoz
 * @since 27/04/2021
 * @version 1.0
 */
public class JSFUtil {

	// retornando mensagem de erro
	public static void addErrorMessage(String title, String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(msg, facesMessage);
	}

	// retornando mensagem de info
	public static void addInfoMessage(String title, String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(msg, facesMessage);
	}

}
