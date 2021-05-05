package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.util.HibernateUtil;

public class LocacaoDAO extends GenericDAO {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Locacao> buscarDevolvido(String devolvido) throws Exception {
        ArrayList<Locacao> listaRetorno = new ArrayList<Locacao>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Locacao.class);
        criteria.add(Restrictions.eq("devolvido", devolvido));
        criteria.addOrder(Order.asc("idLocacao"));
        listaRetorno = (ArrayList<Locacao>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

}
