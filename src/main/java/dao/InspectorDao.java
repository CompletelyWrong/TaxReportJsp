package dao;

import entity.user.InspectorEntity;

import java.util.List;
import java.util.Optional;

public interface InspectorDao extends CrudDao<InspectorEntity, Long> {
    Optional<InspectorEntity> findByLogin(String login);

    Optional<InspectorEntity> findByUserId(Long id);

    List<InspectorEntity> findAllExceptThisId(Long id);
}
