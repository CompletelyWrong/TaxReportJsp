package dao;

import entity.request.RequestEntity;

public interface RequestDao extends CrudDao<RequestEntity, Long> {
    void deleteById(Long requestId);
}
