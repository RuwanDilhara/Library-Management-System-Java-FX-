package repository.custome.impl;

import config.HibernateConfig;
import entity.BookEntity;
import entity.FineEntity;
import org.hibernate.Session;
import repository.custome.FineDao;

import java.util.List;

public class FineDaoImpl implements FineDao  {

    @Override
    public List<FineEntity> getAll() {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        List<FineEntity> fromFineEntity = session.createQuery("FROM FineEntity", FineEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        fromFineEntity.forEach(e -> {
            System.out.println(e);
        });
        return fromFineEntity;
    }

    @Override
    public boolean save(FineEntity entity) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(FineEntity entity) {
        return false;
    }

    @Override
    public boolean delete(FineEntity book) {
        return false;
    }

    @Override
    public FineEntity search(String string) {
        return null;
    }
}
