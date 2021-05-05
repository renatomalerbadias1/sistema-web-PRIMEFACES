package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe responsavel por controlar os componentes Locação
 * 
 *@author Renato
 * @since 05/05/2021
 * @version 1.0
 */

@ManagedBean(name = "locacaoBacking")
@ViewScoped
public class LocacaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private int clienteCombo;
	private int filmeCombo;
	private String valor;
	private String dataLocacao;
	private Date dataDevolucao;
	private int formaPagamentoCombo;

	private double valorTotal;
	private boolean bloqueio;

	private Locacao locacao;
	private Filme filmeSelecionado;

	private ArrayList<Cliente> listaClientes;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<Filme> listaFilmesLocacao;
	private ArrayList<FormaPagamento> listaFormaPagamento;
	
	
	// metodo construtor da classe

	public LocacaoBacking() {
		carregarClientes();
		carregarFilmes();
		carregarFormaPagamentos();
		carregarDataLocacao();
		listaFilmesLocacao = new ArrayList<Filme>();
		setValor(NumberFormat.getCurrencyInstance().format(Constantes.DOUBLE_ZERO));
		setBloqueio(true);
	}
	
	/*
	 * 
	 * Metodos get e set
	 */

	public int getClienteCombo() {
		return clienteCombo;
	}

	public void setClienteCombo(int clienteCombo) {
		this.clienteCombo = clienteCombo;
	}

	public int getFilmeCombo() {
		return filmeCombo;
	}

	public void setFilmeCombo(int filmeCombo) {
		this.filmeCombo = filmeCombo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public int getFormaPagamentoCombo() {
		return formaPagamentoCombo;
	}

	public void setFormaPagamentoCombo(int formaPagamentoCombo) {
		this.formaPagamentoCombo = formaPagamentoCombo;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public ArrayList<Filme> getListaFilmesLocacao() {
		return listaFilmesLocacao;
	}

	public void setListaFilmesLocacao(ArrayList<Filme> listaFilmesLocacao) {
		this.listaFilmesLocacao = listaFilmesLocacao;
	}

	public ArrayList<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(ArrayList<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}
	
	
	
	/***
	 * 
	 * metodo para salvar
	 */

	public void salvar() {

		if (validar()) {

			try {
				getLocacao();
				new LocacaoController().salvar(locacao);

				salvarLocacaoFilmes();

				cancelar();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_SALVO);
			}

		}

	}
	/*
	 * metodo para retornar objeto locação
	 */

	private void getLocacao() {
		locacao = new Locacao();

		locacao.setDataLocacao(dataLocacao);
		locacao.setDataDevolucao(getDateToString());

		locacao.setValor(valorTotal);
		locacao.setDevolvido(Constantes.NAO);

		locacao.setFuncionarioIdFuncionario(LoginBacking.funcionarioLogado);
		locacao.setFormaPagamentoIdFormaPagamento(getFormaPagamentoLista());
		locacao.setClienteIdCliente(getClienteLista());

	}
	
	/*
	 * metodo para validar campos
	 */

	private boolean validar() {

		if (Valida.isIntZero(clienteCombo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.CLIENTE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(listaFilmesLocacao.size())) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_VAZIO);
			return false;
		}

		if (Valida.isIntZero(formaPagamentoCombo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FORMA_PAGAMENTO_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataDevolucao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.DATA_DEVOLUCAO_VAZIO);
			return false;
		}

		return true;
	}
	/*
	 * metodo para cancelar
	 */

	public void cancelar() {
		setClienteCombo(Constantes.INT_ZERO);
		setBloqueio(true);
		limparCampos();
	}
	
	/*
	 * metodo para sair
	 */

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * metodo para limpar campos
	 */

	private void limparCampos() {
		carregarFilmes();
		setFilmeCombo(Constantes.INT_ZERO);
		listaFilmesLocacao = new ArrayList<Filme>();
		setFormaPagamentoCombo(Constantes.INT_ZERO);
		setDataDevolucao(null);
		valorTotal = Constantes.DOUBLE_ZERO;
		setValor(NumberFormat.getCurrencyInstance().format(Constantes.DOUBLE_ZERO));
	}
	
	/*
	 * metodo para carregar Cliente
	 */

	private void carregarClientes() {
		try {
			listaClientes = new ClienteController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * metodo para carregar filme
	 */

	private void carregarFilmes() {
		try {
			listaFilmes = new FilmeController().buscarDisponivel(Constantes.SIM);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * metodo para carregar forma de pagamento
	 */

	private void carregarFormaPagamentos() {
		try {
			listaFormaPagamento = new FormaPagamentoController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * metodo para carregar dados da locação
	 */

	private void carregarDataLocacao() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat(Constantes.FORMATO_DATA);
		dataLocacao = formatador.format(data);
	}

	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat(Constantes.FORMATO_DATA);
		return format.format(dataDevolucao);
	}
	
	/*
	 * metodo para adicionar valor e tipo cliente
	 * 
	 */

	public void adicionar() {
		if (filmeCombo > Constantes.INT_ZERO) {
			int index = listaFilmes.indexOf(new Filme(filmeCombo));
			Filme filme = listaFilmes.get(index);

			if (filme.getFaixaEtaria() > getClienteLista().getIdade()) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, "Filme não permitido para o cliente selecionado!");
			} else {
				listaFilmesLocacao.add(filme);
				listaFilmes.remove(filme);

				Collections.sort(listaFilmesLocacao, Filme.FilmeComparator);

				if (filme.getPromocao().equals(Constantes.SIM)) {
					valorTotal += filme.getValorPromocao();
				} else {
					valorTotal += filme.getValor();
				}
				valor = NumberFormat.getCurrencyInstance().format(valorTotal);
			}

		} else {
			JSFUtil.addErrorMessage("Efetuar Locação", "Selecione um filme!");
		}

	}
	/*
	 * metodo para excluir
	 */

	public void excluir() {
		listaFilmesLocacao.remove(filmeSelecionado);
		listaFilmes.add(filmeSelecionado);

		Collections.sort(listaFilmes, Filme.FilmeComparator);

		if (filmeSelecionado.getPromocao().equals(Constantes.SIM)) {
			valorTotal -= filmeSelecionado.getValorPromocao();
		} else {
			valorTotal -= filmeSelecionado.getValor();
		}
		valor = NumberFormat.getCurrencyInstance().format(valorTotal);

	}
	
	/*
	 * metodo para salvar Filme
	 */

	private void salvarLocacaoFilmes() {
		for (Filme filme : listaFilmesLocacao) {
			try {
				LocacaoFilme locacaoFilme = new LocacaoFilme();
				locacaoFilme.setFilmeIdFilme(filme);
				locacaoFilme.setLocacaoIdLocacao(locacao);

				new LocacaoFilmeController().salvar(locacaoFilme);

				filme.setDisponivel(Constantes.NAO);
				new FilmeController().salvar(filme);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private FormaPagamento getFormaPagamentoLista() {
		int indFormaPagamento = listaFormaPagamento.indexOf(new FormaPagamento(formaPagamentoCombo));
		return listaFormaPagamento.get(indFormaPagamento);
	}

	private Cliente getClienteLista() {
		int indCliente = listaClientes.indexOf(new Cliente(clienteCombo));
		return listaClientes.get(indCliente);
	}
	
	/*
	 * metodo para desbloquear 
	 */

	public void desbloqueioFilmes() {
		if (clienteCombo > Constantes.INT_ZERO) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limparCampos();
	}
	
	

}
