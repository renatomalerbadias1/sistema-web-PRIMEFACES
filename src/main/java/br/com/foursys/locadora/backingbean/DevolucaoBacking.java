package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;

/**
 * Classe responsavel por controlar os componentes do front-end Devolução
 * 
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 */

@ManagedBean(name = "devolucaoBacking")
@ViewScoped
public class DevolucaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private int locacao;
	private ArrayList<Locacao> listaLocacoes;

	private String nomeCliente;

	private String dataLocacao;
	private String dataDevolucaoLocacao;
	private String dataDevolucaoAtual;
	private int filmeCombo;

	public int getFilmeCombo() {
		return filmeCombo;
	}


	public void setFilmeCombo(int filmeCombo) {
		this.filmeCombo = filmeCombo;
	}



	private ArrayList<Filme> listaFilmes;
	private ArrayList<Filme> listaFilmesLocacao;

	private boolean bloqueio;
	private boolean carregado;

	private Locacao devolucao;
	private double valorTotal;
	private int valor;
	private Filme filmeSelecionado;
	
	
	

	public ArrayList<Filme> getListaFilmesLocacao() {
		return listaFilmesLocacao;
	}


	public void setListaFilmesLocacao(ArrayList<Filme> listaFilmesLocacao) {
		this.listaFilmesLocacao = listaFilmesLocacao;
	}


	public double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}


	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}


	public DevolucaoBacking() {
		carregarLocacoes();
	}


	public int getLocacao() {
		return locacao;
	}

	public void setLocacao(int locacao) {
		this.locacao = locacao;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getDataDevolucaoLocacao() {
		return dataDevolucaoLocacao;
	}

	public void setDataDevolucaoLocacao(String dataDevolucaoLocacao) {
		this.dataDevolucaoLocacao = dataDevolucaoLocacao;
	}

	public String getDataDevolucaoAtual() {
		return dataDevolucaoAtual;
	}

	public void setDataDevolucaoAtual(String dataDevolucaoAtual) {
		this.dataDevolucaoAtual = dataDevolucaoAtual;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public boolean isCarregado() {
		return carregado;
	}

	public void setCarregado(boolean carregado) {
		this.carregado = carregado;
	}
	
	/*
	 * Metodo para carregar locação
	 */

	public void carregarLocacao() {
		if (locacao > Constantes.INT_ZERO) {
			carregarTela();
		} else {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_DEVOLUCAO, "Selecione uma locação!");
		}
	}
	
	/**
	 * 
	 * Metododo para salvar
	 */

	public void salvar() {

		if (validar()) {

			try {
				getDevolucao();
				new LocacaoController().salvar(devolucao);

				alterarFilmesLocacao();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_SALVO);
				cancelar();
				carregarLocacoes();
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_ERRO_SALVO);
			}
		}
	}
	
	/**
	 * 
	 * Metododo para validar
	 */

	private boolean validar() {

		return true;
	}
	
	/**
	 * 
	 * Metododo para cancelar
	 */

	public void cancelar() {
		setLocacao(Constantes.INT_ZERO);
		limparCampos();
		setBloqueio(true);
		setCarregado(false);
	}

	
	/**
	 * 
	 * Metododo para sair
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Metododo para limpar campos
	 */

	private void limparCampos() {
		setNomeCliente(null);
		setDataLocacao(null);
		setDataDevolucaoLocacao(null);
		setDataDevolucaoAtual(null);
		listaFilmes = new ArrayList<Filme>();
	}
	
	/**
	 * 
	 * Metododo para carregar loaçoes
	 */

	private void carregarLocacoes() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido(Constantes.NAO);
			setBloqueio(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Metododo para carregar tela
	 */

	private void carregarTela() {
		listaFilmes = new ArrayList<Filme>();
		devolucao = getLocacaoLista();
		setNomeCliente(devolucao.getClienteIdCliente().getNome());
		setDataLocacao(devolucao.getDataLocacao());
		setDataDevolucaoLocacao(devolucao.getDataDevolucao());
		setDataDevolucaoAtual(getDataAtual());

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(devolucao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}

		setBloqueio(true);
		setCarregado(true);
	}
	
	/**
	 * 
	 * Metododo para devoluçao
	 */

	private void getDevolucao() {
		devolucao.setDevolvido(Constantes.SIM);
		if (!dataDevolucaoLocacao.equals(dataDevolucaoAtual)) {
			devolucao.setDataDevolucao(dataDevolucaoAtual);
		}
	}
	
	/**
	 * 
	 * Metododo para alterar 
	 */

	private void alterarFilmesLocacao() {
		for (Filme filme : listaFilmes) {
			filme.setDisponivel(Constantes.SIM);

			try {
				new FilmeController().salvar(filme);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * Metododo para retornar lista locaçao
	 */

	private Locacao getLocacaoLista() {
		int indLocacao = listaLocacoes.indexOf(new Locacao(locacao));
		
		return listaLocacoes.get(indLocacao);
	}
	/**
	 * 
	 * Metododo para desbloqueio
	 */

	public void desbloqueioDevolucao() {
		if (locacao > Constantes.INT_ZERO) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limparCampos();
	}
	
	
	/**
	 * 
	 * Metododo para pegar data atual
	 */

	private String getDataAtual() {
		return new SimpleDateFormat(Constantes.FORMATO_DATA).format(new Date());
	}
	
	
	
	/**
	 * 
	 * Metododo para adicionar valor total
	 */
	
	@SuppressWarnings("unlikely-arg-type")
	public void adicionar() {
		
		
		if (listaFilmesLocacao.equals(valor)) {
			
		valor +=valor;
			
		}

		
	

	}



	

	
}



