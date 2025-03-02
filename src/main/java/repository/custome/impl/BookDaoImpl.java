package repository.custome.impl;

import config.HibernateConfig;
import entity.BookEntity;
import org.hibernate.Session;
import repository.custome.BookDao;
import util.enums.BookStatus;

import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean save(BookEntity entity) {
        return false;
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
    public boolean delete(String id) {
        return false;
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
        session.getTransaction();
        session.close();
        fromBookEntity.forEach(e->{
            System.out.println(e);
        });
        return fromBookEntity;
    }
}
