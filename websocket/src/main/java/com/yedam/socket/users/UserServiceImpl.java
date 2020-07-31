package com.yedam.socket.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	UserDAOMybatis userDAO;
	
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

	@Override
	public List<Map> getRole(UserVO vo) {
		return userDAO.getRole(vo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO vo = new UserVO();
		vo.setLoginId(username);
		vo = userDAO.getUser(vo);
		if(vo == null) {
			throw new UsernameNotFoundException("no user");
		}
		vo.setAuthorities(getAuthorities(vo));
		return vo;
	}
	
	public Collection<GrantedAuthority> getAuthorities(UserVO vo){
		List<Map> authList = userDAO.getRole(vo);
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Map map : authList) {
			authorities.add(new SimpleGrantedAuthority(
					(String)map.get("ROLE_NAME")));
		}
		return authorities;
		
	}
	
	


}
