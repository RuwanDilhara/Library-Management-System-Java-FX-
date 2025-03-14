package repository.custome.impl;

import config.HibernateConfig;
import entity.BorrowBookDetailsEntity;
import org.hibernate.Session;
import repository.custome.BorrowBookDetailsDao;

import java.util.List;

public class BorrowBookDetailsDaoImpl implements BorrowBookDetailsDao {
    @Override
    public boolean save(BorrowBookDetailsEntity entity) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(BorrowBookDetailsEntity entity) {
        return false;
    }

    @Override
    public boolean delete(BorrowBookDetailsEntity book) {
        return false;
    }

    @Override
    public BorrowBookDetailsEntity search(String string) {
        return null;
    }

    @Override
    public List<BorrowBookDetailsEntity> getAll() {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        List<BorrowBookDetailsEntity> fromBorrowedBookDetailsEntity = session.createQuery("FROM BorrowBookDetailsEntity",BorrowBookDetailsEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        fromBorrowedBookDetailsEntity.forEach(e -> {
            System.out.println(e);
        });
        return fromBorrowedBookDetailsEntity;
    }
}
