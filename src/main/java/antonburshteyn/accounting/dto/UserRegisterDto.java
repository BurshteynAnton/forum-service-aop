package antonburshteyn.accounting.dto;

import lombok.Getter;

@Getter
public class UserRegisterDto {
	String login;
    String password;
    String firstName;
    String lastName;
    String email;
}
