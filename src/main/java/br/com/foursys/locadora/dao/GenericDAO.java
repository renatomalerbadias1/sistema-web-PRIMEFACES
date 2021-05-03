package br.com.foursys.locadora.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.foursys.locadora.util.HibernateUtil;

public abstract class GenericDAO {

	public void salvar(Object object) throws Exception{
		Session sessao = null;
		Transaction transacao = null;

		sessao = HibernateUtil.getSessionFactory().openSession();
		transacao = sessao.beginTransaction();

		sessao.saveOrUpdate(object);
		transacao.commit();
		sessao.close();

	}

	public void excluir(Object object) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(object);
			transacao.commit();
		} catch (Exception e) {
			transacao.rollback();
			sessao.close();
		} finally {
			sessao.close();
		}

	}

}
