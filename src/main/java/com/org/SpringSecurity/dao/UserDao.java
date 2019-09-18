package com.org.SpringSecurity.dao;

import com.org.SpringSecurity.model.Login;
import com.org.SpringSecurity.model.User;

public interface UserDao {
	void register(User user);

	User validateUser(Login login);
}
