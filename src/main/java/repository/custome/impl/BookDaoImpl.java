package repository.custome.impl;

import config.HibernateConfig;
import dto.Book;
import entity.BookEntity;
import org.hibernate.Session;
import repository.custome.BookDao;

import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean save(BookEntity entity) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(BookEntity entity) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return true;


    }

    @Override
    public boolean delete(BookEntity book) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.remove(book);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public BookEntity search(String string) {
        return null;
    }

    @Override
    public List<BookEntity> getAll() {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        List<BookEntity> fromBookEntity = session.createQuery("FROM BookEntity", BookEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        fromBookEntity.forEach(e -> {
            System.out.println(e);
        });
        return fromBookEntity;
    }
}
