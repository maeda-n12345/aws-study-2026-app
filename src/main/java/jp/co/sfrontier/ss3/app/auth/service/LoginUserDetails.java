package jp.co.sfrontier.ss3.app.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.sfrontier.ss3.app.user.entity.UserInformation;
import lombok.RequiredArgsConstructor;

/**
 * DB のユーザー情報を使って Spring Security の認証を行うクラス
 */
@RequiredArgsConstructor
public class LoginUserDetails implements UserDetails{

	private final UserInformation user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();

		// ユーザ名が admin の場合だけ管理者ユーザ、それ以外は一般ユーザとする
		if ("admin".equals(user.getUserName())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getStatus() == 1;
	}

	public UserInformation getUser() {
		return user;
	}
}
