package dk.reshr.user.api.xternal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.reshr.user.api.model.User;
import dk.reshr.user.api.repository.UserDao;
import dk.reshr.user.api.repository.UserRepository;
import dk.reshr.user.api.sevice.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDataServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Integer userId) {
		LOGGER.debug("findById {}", userId);
		UserDao dao = userRepository.findById(userId).get();
		return toUser(dao);
	}

	@Override
	public User saveUser(User user) {
		UserDao d = new UserDao();
		d.setId(user.getId());
		d.setEmail(user.getEmail());
		userRepository.save(d);
		return user;
	}

	@Override
	public List<User> fetchUserList() {
		LOGGER.debug("toPallets {}");
		List<UserDao> users = new ArrayList<UserDao>();
        Iterable<UserDao> iter = userRepository.findAll();
        iter.forEach((p) -> {
    		LOGGER.info("fetchPalletList() --");

            users.add(p);
        });
		return toUsers(users);
	}

	@Override
	public User updateUser(User user, Integer userId) {
		UserDao d = userRepository.findById(userId).get();
		d.setEmail(user.getEmail());

		return user;
	}

	@Override
	public void deleteUserById(Integer userId) {
		userRepository.deleteById(userId);
	}

	private List<User> toUsers(List<UserDao> users) {
		LOGGER.info("toUsers {}", users.size());
		List<User> rtn = users.stream().map(p -> toUser(p)).collect(Collectors.toList());
		return rtn;
	}

	private User toUser(UserDao id) {
		LOGGER.info("toUser {}", id.getId());
		User d = new User();
		d.setId(id.getId());
		d.setEmail(id.getEmail());
		return d;
	}

}
