package dao;

import entity.user.InspectorEntity;

import java.util.Optional;

public interface InspectorDao extends CrudDao<InspectorEntity, Long> {
    Optional<InspectorEntity> findByEmail(String email);

    Optional<InspectorEntity> findByUserId(Long userId);

    Optional<InspectorEntity> findWithLessUsers();

    Optional<InspectorEntity> findWithLessUsersExceptThisId(Long inspectorId);
}
