package repository;

import java.util.List;

public interface CrudRepository<T,ID> extends SuperDao{
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(String id);
    T search(ID id);
    List<T> getAll();
}
