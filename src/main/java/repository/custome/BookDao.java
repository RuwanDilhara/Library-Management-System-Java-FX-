package repository.custome;

import entity.BookEntity;
import repository.CrudRepository;

public interface BookDao extends CrudRepository<BookEntity,String> {
}
