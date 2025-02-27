package config;

import entity.BookEntity;
import entity.MemberEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {
    private static SessionFactory session = createSession();

    private static SessionFactory createSession(){
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(BookEntity.class)
                .addAnnotatedClass(MemberEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        return metadata.getSessionFactoryBuilder().build();
    }
    public static Session getSession(){
        return session.openSession();
    }
}
