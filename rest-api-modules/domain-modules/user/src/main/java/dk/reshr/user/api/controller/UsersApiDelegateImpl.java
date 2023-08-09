package dk.reshr.user.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.reshr.user.api.UsersApiDelegate;
import dk.reshr.user.api.model.User;
import dk.reshr.user.api.sevice.UserDataService;

@Service
public class UsersApiDelegateImpl implements UsersApiDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersApiDelegateImpl.class);

	@Autowired
    private UserDataService userDataService;

	@Override
	public ResponseEntity<Void> createUser(User user) {
		LOGGER.info("createUser id {}", user.getId());
		userDataService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<User>> listUsers(Integer limit) {
		LOGGER.info("listUsers {}", limit);
		List<User> users = new ArrayList<>();
		users.addAll(userDataService.fetchUserList());
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> showUserById(Integer userId) {
		LOGGER.info("showUserById {}", userId);
		User user = userDataService.findById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
