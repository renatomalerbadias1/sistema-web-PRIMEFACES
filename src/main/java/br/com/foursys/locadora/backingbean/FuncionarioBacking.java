package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * @author Renato
 * @since 05/05/2021
 * @version 1.0
 *
 */

@ManagedBean(name = "funcionarioBacking")
@SessionScoped
public class FuncionarioBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos dados básicos da tela de cadastro
	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private String idade;
	private String sexo;
	private String login;
	private String senha;
	private String perfilAcesso;

	// atributos endereco da tela de cadastro
	private String tipoLogradouro;
	private String nomeEndereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private int cidade;
	private int estado;

	// atributos contato da tela de cadastro
	private String telefone;
	private String celular;
	private String email;

	// atributos da tela de consulta
	private String nomePesquisar;
	private Funcionario funcionarioSelecionado;

	// atributos auxiliares
	private Funcionario funcionario;
	private Endereco endereco;
	private Contato contato;

	private ArrayList<Funcionario> listaFuncionarios;
	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<String> listaPerfis;
	private ArrayList<String> listaTipoLogradouros;

	private boolean comboCidade = true;
	private int indexTab;

	/*
	 * Metodo costrutor 
	 */
	public FuncionarioBacking() {
		carregarPerfil();
		carregarTipoLogradouro();
		carregarEstados();
		limparCampos();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(String perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public ArrayList<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(ArrayList<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public boolean isComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(boolean comboCidade) {
		this.comboCidade = comboCidade;
	}

	public int getIndexTab() {
		return indexTab;
	}

	public void setIndexTab(int indexTab) {
		this.indexTab = indexTab;
	}

	public ArrayList<String> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(ArrayList<String> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}

	public ArrayList<String> getListaTipoLogradouros() {
		return listaTipoLogradouros;
	}

	public void setListaTipoLogradouros(ArrayList<String> listaTipoLogradouros) {
		this.listaTipoLogradouros = listaTipoLogradouros;
	}
	
	
	/*
	 * metodo para cadastrar
	 */

	public void cadastrar() {
		if (validar()) {
			try {
				getFuncionario();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				new FuncionarioController().salvar(funcionario);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}
	
	/*
	 * Metodo para alterar
	 */

	public void alterarFuncionario() {
		if (validar()) {
			try {
				getFuncionarioAlterar();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}
	
	/*
	 * 
	 * metodo para validar
	 */

	private boolean validar() {
		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CPF_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.RG_VAZIO);
			return false;
		}
		if (Valida.isDateNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(idade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SEXO_VAZIO);
			return false;
		}

		return true;
	}
	
	/*
	 * Metodo para cancelar
	 */

	public void cancelar() {
		limparCampos();
	}
	
	/*
	 * Metodo para sair
	 */

	public void sair() {
		try {
			limparCampos();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Metodo para retornar obj funcionario
	 */
	

	private void getFuncionario() {
		funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(getDateToString());
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(perfilAcesso);
		getEndereco();
		getContato();
		funcionario.setEnderecoIdEndereco(endereco);
		funcionario.setContatoIdContato(contato);
	}
	
	/*
	 * Metodo para retornar obj endereco
	 */

	private void getEndereco() {
		endereco = new Endereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(nomeEndereco);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}
	
	/*
	 * Metodo para retornar obj contato
	 */

	private void getContato() {
		contato = new Contato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	/*
	 * Metodo para alterar obj funcionario
	 */

	private void getFuncionarioAlterar() {
		funcionario = funcionarioSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		funcionario.setEnderecoIdEndereco(endereco);
		funcionario.setContatoIdContato(contato);
	}
	
	/*
	 * Metodo para alterar obj endereco
	 */


	private void getEnderecoAlterar() {
		endereco = funcionario.getEnderecoIdEndereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(nomeEndereco);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}
	
	/*
	 * Metodo para alterar obj contato
	 */


	private void getContatoAlterar() {
		contato = funcionario.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	/*
	 * Metodo para limpar campos
	 */


	private void limparCampos() {
		// dados basicos
		setNome(null);
		setCpf(null);
		setRg(null);
		setDataNascimento(null);
		setIdade(null);
		setSexo(null);
		setLogin(null);
		setSenha(null);
		setPerfilAcesso(null);
		// dados de endereco
		setTipoLogradouro(null);
		setNomeEndereco(null);
		setNumero(null);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setEstado(0);
		setCidade(0);
		setComboCidade(true);
		// dados de contato
		setTelefone(null);
		setCelular(null);
		setEmail(null);
		setListaFuncionarios(null);
		setNomePesquisar(null);
		setIndexTab(0);
	}
	
	/*
	 * Metodo para alterar pesquisar
	 */


	public void pesquisar() {
		try {
			listaFuncionarios = new FuncionarioController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISA);
		}
	}
	
	/*
	 * Metodo para alterar 
	 */


	public void alterar() throws ParseException {
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(funcionarioSelecionado.getDataNascimento()).getTime());;
		
		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		carregarEstados();
		carregarCidadesAlterar();
		tipoLogradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		nomeEndereco = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		estado = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * Metodo para excluir
	 */


	public void excluir() {
		try {
			new FuncionarioController().excluir(funcionarioSelecionado);
			new EnderecoController().excluir(funcionarioSelecionado.getEnderecoIdEndereco());
			new ContatoController().excluir(funcionarioSelecionado.getContatoIdContato());
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_EXCLUIDO);
		}
	}

	/*
	 * Metodo para  carregar estados
	 */

	
	private void carregarEstados() {
		listaEstados = new ArrayList<Estado>();
		try {
			listaEstados = new EstadoController().buscarTodos();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ESTADO_ERRO_PESQUISA);
		}
	}

	/*
	 * Metodo para  carregar perfil
	 */
	
	private void carregarPerfil() {
		listaPerfis = new ArrayList<String>();
		for (Perfil p : Perfil.values()) {
			listaPerfis.add(p.getDescricao());
		}
	}
	
	/*
	 * Metodo para  carregar TipoLogradouro
	 */

	private void carregarTipoLogradouro() {
		listaTipoLogradouros = new ArrayList<String>();
		for (Logradouro l : Logradouro.values()) {
			listaTipoLogradouros.add(l.getDescricao());
		}
	}
	
	/*
	 * Metodo para  carregar Cidade
	 */

	public void carregarCidades() {
		listaCidades = new ArrayList<Cidade>();
		try {
			if (estado > 0) {
				listaCidades = new CidadeController().buscarPorEstado(new Estado(estado));
				comboCidade = false;
			} else {
				comboCidade = true;
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}
	
	/*
	 * Metodo para  carregar CidadesAlterar(
	 */

	public void carregarCidadesAlterar() {
		listaCidades = new ArrayList<Cidade>();
		try {
			listaCidades = new CidadeController().buscarTodos();
			comboCidade = false;
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(dataNascimento);
	}

}
