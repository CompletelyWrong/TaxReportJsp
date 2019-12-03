package service;

import domain.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User findById(Long id);

    User login(String login, String password);

    List<User> findAll(int rowCount, int startFrom);

    void updateInfo(User user);

    Integer getRowNumbers();

    void changeInspectorForUser(User user);

    Integer getRowCountByInspectorId(Long id);

    List<User> findAllByInspectorId(Long id, int rowCount, int startFrom);
}
