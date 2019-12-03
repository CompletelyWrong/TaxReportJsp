package service.mapper;

import domain.Inspector;
import entity.user.InspectorEntity;

public class InspectorMapper {
    public InspectorEntity mapInspectorToInspectorEntity(Inspector inspector) {
        return new InspectorEntity.InspectorBuilder()
                .withEmail(inspector.getEmail())
                .withName(inspector.getName())
                .withId(inspector.getId())
                .withPassword(inspector.getPassword())
                .withPatronymic(inspector.getPatronymic())
                .withSurname(inspector.getSurname())
                .withRole(inspector.getRole())
                .build();
    }

    public Inspector mapInspectorEntityToInspector(InspectorEntity entity) {
        return Inspector.builder()
                .withEmail(entity.getEmail())
                .withName(entity.getName())
                .withId(entity.getId())
                .withPassword(entity.getPassword())
                .withPatronymic(entity.getPatronymic())
                .withSurname(entity.getSurname())
                .withRole(entity.getRole())
                .build();
    }
}
