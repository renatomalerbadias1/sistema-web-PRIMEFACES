package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.util.HibernateUtil;

public class ClienteDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Cliente> buscarTodos() throws Exception {

        ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cliente.class);
        criteria.addOrder(Order.asc("idCliente"));
        listaRetorno = (ArrayList<Cliente>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Cliente> buscarPorNome(String nome) throws Exception {

        ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cliente.class);
        criteria.add(Restrictions.like("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Cliente>) criteria.list();
        sessao.close();
        return listaRetorno;
    }
    
    public Cliente buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Cliente filme = (Cliente) sessao.get(Cliente.class, codigo);
        sessao.close();
        return filme;
    }

}
