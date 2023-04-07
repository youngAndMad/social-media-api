package danekerscode.socialmediaapi.service.i;

import java.util.List;
import java.util.Optional;

public interface ParentService <T>{
    T save(Object t);
    void deleteByID(Integer id);
    Optional<T> getById(Integer id);
   default void update(T t){}
    List<T> getAll();
}
