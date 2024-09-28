package antonburshteyn.accounting.service;

import antonburshteyn.accounting.dao.UserAccountRepository;
import antonburshteyn.accounting.dto.RolesDto;
import antonburshteyn.accounting.dto.UserDto;
import antonburshteyn.accounting.dto.UserEditDto;
import antonburshteyn.accounting.dto.UserRegisterDto;
import antonburshteyn.accounting.dto.exceptions.UserExistsException;
import antonburshteyn.accounting.dto.exceptions.UserNotFoundException;
import antonburshteyn.accounting.model.Role;
import antonburshteyn.accounting.model.UserAccount;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, CommandLineRunner {
	
	final UserAccountRepository userAccountRepository;
	final ModelMapper modelMapper;
	final PasswordEncoder passwordEncoder;

//	@Override
//	public UserDto register(UserRegisterDto userRegisterDto) {
//		if (userAccountRepository.existsByLogin(userRegisterDto.getLogin())) {
//			throw new UserExistsException();
//		}
//		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
//		String password = passwordEncoder.encode(userRegisterDto.getPassword());
//		userAccount.setPassword(password);
//		userAccount.addRole("USER");
//		userAccountRepository.save(userAccount);
//		return modelMapper.map(userAccount, UserDto.class);
//	}

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		System.out.println("Registration user with login: " + userRegisterDto.getLogin());

		if (userAccountRepository.existsByLogin(userRegisterDto.getLogin())) {
			System.out.println("User already exist: " + userRegisterDto.getLogin());
			throw new UserExistsException();
		}
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
		String password = passwordEncoder.encode(userRegisterDto.getPassword());
		userAccount.setPassword(password);
		userAccount.addRole("USER");
		userAccount.setEnabled(true);
		userAccountRepository.save(userAccount);

		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		UserAccount userAccount = userAccountRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto removeUser(String login) {
		UserAccount userAccount = userAccountRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
		userAccountRepository.delete(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		UserAccount userAccount = userAccountRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
		if (userEditDto.getFirstName() != null) {
			userAccount.setFirstName(userEditDto.getFirstName());
		}
		if (userEditDto.getLastName() != null) {
			userAccount.setLastName(userEditDto.getLastName());
		}
		userAccountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount userAccount = userAccountRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
		boolean res;
		if (isAddRole) {
			res = userAccount.addRole(role);
		} else {
			res = userAccount.removeRole(role);
		}
		if(res) {
			userAccountRepository.save(userAccount);
		}
		return modelMapper.map(userAccount, RolesDto.class);
	}

	@Override
	public void changePassword(String username, String currentPassword, String newPassword) {
		UserAccount userAccount = userAccountRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not Found"));

		if (!passwordEncoder.matches(currentPassword, userAccount.getPassword())) {
			throw new IllegalArgumentException("Password not correct");
		}

		String encodedNewPassword = passwordEncoder.encode(newPassword);
		userAccount.setPassword(encodedNewPassword);
		userAccountRepository.save(userAccount);
	}

//	@Override
//	public void changePassword(String login, String newPassword) {
//		UserAccount userAccount = userAccountRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
//		String password = passwordEncoder.encode(newPassword);
//		userAccount.setPassword(password);
//		userAccountRepository.save(userAccount);
//
//	}

	@Override
	public void run(String... args) throws Exception {
		if (!userAccountRepository.existsByLogin("admin")) {
			String password = passwordEncoder.encode("admin");
			UserAccount userAccount = new UserAccount("admin", "", "", "admin@example.com", password);
			userAccount.addRole(Role.MODERATOR.name());
			userAccount.addRole(Role.ADMINISTRATOR.name());
			userAccountRepository.save(userAccount);
		}
	}

}
