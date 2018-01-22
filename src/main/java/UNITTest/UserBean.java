package UNITTest;

import java.io.Serializable;

/**
 * Created by kingcall 2017年-08月-27日,13时-17分
 * Descibe
 */
public class UserBean implements Serializable{
    private String id;
    private String username;
    private String password;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
