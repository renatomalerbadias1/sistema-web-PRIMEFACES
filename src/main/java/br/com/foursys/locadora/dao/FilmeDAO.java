package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.util.HibernateUtil;

public class FilmeDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Filme> buscarTodos() throws Exception {
        ArrayList<Filme> listaRetorno = new ArrayList<Filme>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Filme.class);
        criteria.addOrder(Order.asc("idFilme"));
        listaRetorno = (ArrayList<Filme>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Filme> buscarPorNome(String nome) throws Exception {
        ArrayList<Filme> listaRetorno = new ArrayList<Filme>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Filme.class);
        criteria.add(Restrictions.like("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Filme>) criteria.list();
        sessao.close();
        return listaRetorno;
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Filme> buscarDisponivel(String disponivel) throws Exception {
        ArrayList<Filme> listaRetorno = new ArrayList<Filme>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Filme.class);
        criteria.add(Restrictions.eq("disponivel", disponivel));
        criteria.addOrder(Order.asc("idFilme"));
        listaRetorno = (ArrayList<Filme>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public Filme buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Filme retorno = (Filme) sessao.get(Filme.class, codigo);
        sessao.close();
        return retorno;
    }

}
