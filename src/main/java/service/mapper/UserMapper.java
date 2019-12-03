package service.mapper;

import domain.User;
import entity.user.InspectorEntity;
import entity.user.UserEntity;

public class UserMapper {

    public UserEntity mapUserToUserEntity(User user) {
        return new UserEntity.UserBuilder()
                .withIdentificationCode(user.getIdentificationCode())
                .withRole(user.getRole())
                .withEmail(user.getEmail())
                .withName(user.getName())
                .withId(user.getId())
                .withPassword(user.getPassword())
                .withPatronymic(user.getPatronymic())
                .withSurname(user.getSurname())
                .build();
    }

    public User mapUserEntityToUser(UserEntity entityUser) {
        return User.builder()
                .withIdentificationCode(entityUser.getIdentificationCode())
                .withRole(entityUser.getRole())
                .withEmail(entityUser.getEmail())
                .withName(entityUser.getName())
                .withId(entityUser.getId())
                .withPassword(entityUser.getPassword())
                .withPatronymic(entityUser.getPatronymic())
                .withSurname(entityUser.getSurname())
                .build();
    }

    public UserEntity mapUserToUserEntityWithInspectorId(User user, Long id) {
        return new UserEntity.UserBuilder()
                .withIdentificationCode(user.getIdentificationCode())
                .withRole(user.getRole())
                .withEmail(user.getEmail())
                .withName(user.getName())
                .withId(user.getId())
                .withPassword(user.getPassword())
                .withPatronymic(user.getPatronymic())
                .withSurname(user.getSurname())
                .withInspector(new InspectorEntity.InspectorBuilder().withId(id).build())
                .build();
    }

    public UserEntity mapUserEntityToUserEntityWithInspectorId(UserEntity user, Long id) {
        return new UserEntity.UserBuilder()
                .withIdentificationCode(user.getIdentificationCode())
                .withRole(user.getRole())
                .withEmail(user.getEmail())
                .withName(user.getName())
                .withId(user.getId())
                .withPassword(user.getPassword())
                .withPatronymic(user.getPatronymic())
                .withSurname(user.getSurname())
                .withInspector(new InspectorEntity.InspectorBuilder().withId(id).build())
                .build();
    }
}
