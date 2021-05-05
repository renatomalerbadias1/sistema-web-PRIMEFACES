package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.util.HibernateUtil;

public class FormaPagamentoDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<FormaPagamento> buscarTodos() throws Exception {
        ArrayList<FormaPagamento> listaRetorno = new ArrayList<FormaPagamento>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(FormaPagamento.class);
        criteria.addOrder(Order.asc("idFormaPagamento"));
        listaRetorno = (ArrayList<FormaPagamento>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

}
