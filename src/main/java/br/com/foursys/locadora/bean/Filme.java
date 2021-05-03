/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.locadora.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dmunhoz
 */
@Entity
@Table(name = "filme")
@NamedQueries({
    @NamedQuery(name = "Filme.findAll", query = "SELECT f FROM Filme f")})
public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_filme")
    private Integer idFilme;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "disponivel")
    private String disponivel;
    @Basic(optional = false)
    @Column(name = "promocao")
    private String promocao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_promocao")
    private Double valorPromocao;
    @Basic(optional = false)
    @Column(name = "diretor")
    private String diretor;
    @Basic(optional = false)
    @Column(name = "ano_lancamento")
    private String anoLancamento;
    @Basic(optional = false)
    @Column(name = "faixa_etaria")
    private int faixaEtaria;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filmeIdFilme")
    private List<LocacaoFilme> locacaoFilmeList;

    public Filme() {
    }

    public Filme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public Filme(Integer idFilme, String nome, double valor, String disponivel, String promocao, String diretor, String anoLancamento, int faixaEtaria, String genero) {
        this.idFilme = idFilme;
        this.nome = nome;
        this.valor = valor;
        this.disponivel = disponivel;
        this.promocao = promocao;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
        this.faixaEtaria = faixaEtaria;
        this.genero = genero;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public Double getValorPromocao() {
        return valorPromocao;
    }

    public void setValorPromocao(Double valorPromocao) {
        this.valorPromocao = valorPromocao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(int faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<LocacaoFilme> getLocacaoFilmeList() {
        return locacaoFilmeList;
    }

    public void setLocacaoFilmeList(List<LocacaoFilme> locacaoFilmeList) {
        this.locacaoFilmeList = locacaoFilmeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFilme != null ? idFilme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filme)) {
            return false;
        }
        Filme other = (Filme) object;
        if ((this.idFilme == null && other.idFilme != null) || (this.idFilme != null && !this.idFilme.equals(other.idFilme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.foursys.locadora.bean.Filme[ idFilme=" + idFilme + " ]";
    }
    
}
