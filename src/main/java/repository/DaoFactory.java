package repository;

import repository.custome.impl.BookDaoImpl;
import repository.custome.impl.MemberDaoImpl;
import util.enums.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance==null ? new DaoFactory() : instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case BOOK : return (T) new BookDaoImpl();
            case MEMBER: return (T) new MemberDaoImpl();
        }
        return null;
    }
}
