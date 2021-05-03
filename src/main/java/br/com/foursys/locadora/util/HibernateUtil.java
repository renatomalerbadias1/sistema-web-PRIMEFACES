package br.com.foursys.locadora.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
   
    private static SessionFactory buildSessionFactory(){
        return new AnnotationConfiguration().configure().buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
}
