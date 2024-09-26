package antonburshteyn.accounting.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class UserAccount implements UserDetails {

	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
	)
	private Long id;
	private String login;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles = new HashSet<>();

	private Boolean locked = false;
	private Boolean enabled = false;

	public UserAccount(String login, String firstName, String lastName, String email, String password) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles.add(Role.USER);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public boolean addRole(String role) {
		return roles.add(Role.valueOf(role.toUpperCase()));
	}

	public boolean removeRole(String role) {
		return roles.remove(Role.valueOf(role.toUpperCase()));
	}
}
