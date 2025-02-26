package controller.config;

import com.google.inject.AbstractModule;
import repository.custome.BookDao;
import repository.custome.impl.BookDaoImpl;
import service.custome.BookService;
import service.custome.impl.BookServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookDao.class).to(BookDaoImpl.class);
    }
}
