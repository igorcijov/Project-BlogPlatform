package blog.controller.vo;

import blog.domain.User;

public class UserVO {

    private int id;
    private String username;
    private String password;

    private boolean active;

    public UserVO(int id, String username, String password, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static UserVO valueOf(User user) {
        return new UserVO(user.getId(), user.getUsername(), user.getPassword(), user.isActive());
    }
}
