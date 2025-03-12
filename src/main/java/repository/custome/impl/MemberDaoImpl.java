package repository.custome.impl;

import config.HibernateConfig;
import dto.Book;
import entity.BookEntity;
import entity.MemberEntity;
import org.hibernate.Session;
import repository.custome.MemberDao;

import java.util.List;

public class MemberDaoImpl implements MemberDao {
    @Override
    public boolean save(MemberEntity entity) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(MemberEntity entity) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean delete(MemberEntity member) {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.remove(member);
        session.getTransaction().commit();
        session.close();
        return true;

    }

    @Override
    public MemberEntity search(String string) {
        return null;
    }

    @Override
    public List<MemberEntity> getAll() {
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        List<MemberEntity> fromMemberEntity = session.createQuery("FROM MemberEntity", MemberEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        fromMemberEntity.forEach(e -> {
            System.out.println(e);
        });
        return fromMemberEntity;
    }
}
