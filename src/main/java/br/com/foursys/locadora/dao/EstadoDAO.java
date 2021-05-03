package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.util.HibernateUtil;

public class EstadoDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Estado> buscarTodos() throws Exception {
        ArrayList<Estado> listaRetorno = new ArrayList<Estado>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Estado.class);
        criteria.addOrder(Order.asc("idEstado"));
        listaRetorno = (ArrayList<Estado>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

}
