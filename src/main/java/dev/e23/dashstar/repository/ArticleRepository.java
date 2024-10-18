package dev.e23.dashstar.repository;

import dev.e23.dashstar.model.Article;
import dev.e23.dashstar.util.HibernateUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ApplicationScoped
public class ArticleRepository {

    public void create(Article article) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
        em.close();
    }
}
