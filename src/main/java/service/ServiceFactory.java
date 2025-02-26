package service;

import service.custome.impl.BookServiceImpl;
import service.custome.impl.MemberServiceImpl;
import util.enums.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance==null ? new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case BOOK :return (T) new BookServiceImpl();
            case MEMBER: return (T) new MemberServiceImpl();


        }
        return null;
    }

}
