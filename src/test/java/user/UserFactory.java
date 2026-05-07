package user;

import utils.PropertyReader;

public class UserFactory {

    public static User withValidData() {
        return new User(
                PropertyReader.getProperty("skyrexio.user"),
                PropertyReader.getProperty("skyrexio.password"));
    }

    public static User withIncorrectEmailUser() {
        return new User(
                PropertyReader.getProperty("skyrexio.incorrect.email.user"),
                PropertyReader.getProperty("skyrexio.password"));
    }

    public static User withEmptyLogin() {
        return new User((""), PropertyReader.getProperty("skyrexio.password"));
    }

    public static User withEmptyPassword() {
        return new User(PropertyReader.getProperty("skyrexio.user"), (""));
    }
}