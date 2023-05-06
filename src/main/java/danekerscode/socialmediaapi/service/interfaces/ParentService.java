package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.payload.request.Request;

import java.util.List;
import java.util.Optional;

public interface ParentService<T> {

    void deleteByID(Integer id);

    Optional<T> getById(Integer id);

    default void update(Request request , Integer id) {}

    List<T> getAll();
}
