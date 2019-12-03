package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<E, ID> {
    E save(E entity);

    Optional<E> findById(Long id);

    List<E> findAll(Integer rowCount, Integer startFrom);

    List<E> findAll();

    Integer countRows();

    void update(E entity);

    void deleteById(ID id);
}
