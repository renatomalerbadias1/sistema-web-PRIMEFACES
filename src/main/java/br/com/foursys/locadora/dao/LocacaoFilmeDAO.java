package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.util.HibernateUtil;

public class LocacaoFilmeDAO extends GenericDAO {
	
	@SuppressWarnings("unchecked")
	public ArrayList<LocacaoFilme> buscarPorLocacao(Locacao locacao) throws Exception {
        ArrayList<LocacaoFilme> listaRetorno = new ArrayList<LocacaoFilme>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(LocacaoFilme.class);
        criteria.add(Restrictions.eq("locacaoIdLocacao", locacao));
        criteria.addOrder(Order.asc("locacaoIdLocacao"));
        listaRetorno = (ArrayList<LocacaoFilme>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

}
