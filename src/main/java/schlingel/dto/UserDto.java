package schlingel.dto;

import schlingel.domain.User;

public class UserDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;


    public User toDomain(){
        User newUser = new User();
        newUser.setPassword(this.getPassword());
        newUser.setUsername(this.getUsername());
        newUser.setFirstName(this.getFirstName());
        newUser.setLastName(this.getLastName());
        return newUser;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
