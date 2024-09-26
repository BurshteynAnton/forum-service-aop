package antonburshteyn.accounting.service;

import antonburshteyn.accounting.dto.RolesDto;
import antonburshteyn.accounting.dto.UserDto;
import antonburshteyn.accounting.dto.UserEditDto;
import antonburshteyn.accounting.dto.UserRegisterDto;

public interface UserAccountService {
	UserDto register(UserRegisterDto userRegisterDto);

	UserDto getUser(String login);

	UserDto removeUser(String login);

	UserDto updateUser(String login, UserEditDto userEditDto);

	RolesDto changeRolesList(String login, String role, boolean isAddRoles);

	void changePassword(String login, String newPassword);
}
