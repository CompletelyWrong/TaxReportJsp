package service;

import domain.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User findById(Long userId);

    User login(String email, String password);

    List<User> findAll(int rowCount, int startFrom);

    void updateInfo(User user);

    Integer getRowNumbers();

    void changeInspectorForUser(User user);

    Integer getRowCountByInspectorId(Long inspectorId);

    List<User> findAllByInspectorId(Long inspectorId, int rowCount, int startFrom);
}
