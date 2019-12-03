package service.mapper;

import domain.Request;
import domain.User;
import entity.request.RequestEntity;
import entity.user.UserEntity;

public class RequestMapper {
    public RequestEntity mapRequestToRequestEntity(Request request) {
        return new RequestEntity(request.getId(),
                new UserEntity.UserBuilder().withId(request.getUser().getId()).build(),
                request.getReason());
    }

    public Request mapRequestEntityToRequest(RequestEntity entity) {
        return new Request(entity.getId(),
                User.builder().withId(entity.getEntityUser().getId()).build(),
                entity.getReason());
    }
}
