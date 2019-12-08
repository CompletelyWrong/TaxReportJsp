package dao;

import entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAllByInspectorId(Long inspectorId, Integer currentPage, Integer recordsPerPage);

    Integer getRowCountByInspectorId(Long inspectorId);

    void changeInspectorForUserById(UserEntity entity, Long newInspectorId);

    void setInspectorToUser(UserEntity userEntity);
}
