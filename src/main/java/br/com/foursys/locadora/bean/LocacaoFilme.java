/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.locadora.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dmunhoz
 */
@Entity
@Table(name = "locacao_filme")
@NamedQueries({
    @NamedQuery(name = "LocacaoFilme.findAll", query = "SELECT l FROM LocacaoFilme l")})
public class LocacaoFilme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_locacao_filme")
    private Integer idLocacaoFilme;
    @JoinColumn(name = "filme_id_filme", referencedColumnName = "id_filme")
    @ManyToOne(optional = false)
    private Filme filmeIdFilme;
    @JoinColumn(name = "locacao_id_locacao", referencedColumnName = "id_locacao")
    @ManyToOne(optional = false)
    private Locacao locacaoIdLocacao;

    public LocacaoFilme() {
    }

    public LocacaoFilme(Integer idLocacaoFilme) {
        this.idLocacaoFilme = idLocacaoFilme;
    }

    public Integer getIdLocacaoFilme() {
        return idLocacaoFilme;
    }

    public void setIdLocacaoFilme(Integer idLocacaoFilme) {
        this.idLocacaoFilme = idLocacaoFilme;
    }

    public Filme getFilmeIdFilme() {
        return filmeIdFilme;
    }

    public void setFilmeIdFilme(Filme filmeIdFilme) {
        this.filmeIdFilme = filmeIdFilme;
    }

    public Locacao getLocacaoIdLocacao() {
        return locacaoIdLocacao;
    }

    public void setLocacaoIdLocacao(Locacao locacaoIdLocacao) {
        this.locacaoIdLocacao = locacaoIdLocacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocacaoFilme != null ? idLocacaoFilme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocacaoFilme)) {
            return false;
        }
        LocacaoFilme other = (LocacaoFilme) object;
        if ((this.idLocacaoFilme == null && other.idLocacaoFilme != null) || (this.idLocacaoFilme != null && !this.idLocacaoFilme.equals(other.idLocacaoFilme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.foursys.locadora.bean.LocacaoFilme[ idLocacaoFilme=" + idLocacaoFilme + " ]";
    }
    
}
