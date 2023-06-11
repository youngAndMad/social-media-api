package danekerscode.socialmediaapi.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface ParentService<T> {

    void deleteByID(Integer id);

    Optional<T> getById(Integer id);

    List<T> getAll();
}
