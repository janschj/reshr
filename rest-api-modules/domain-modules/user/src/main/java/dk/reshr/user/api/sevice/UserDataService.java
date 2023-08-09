package dk.reshr.user.api.sevice;

import java.util.List;

import dk.reshr.user.api.model.User;


public interface UserDataService {

	// find operation
	User findById(Integer id);

	// Save operation
	User saveUser(User User);

	// Read operation
	List<User> fetchUserList();

	// Update operation
	User updateUser(User user, Integer id);

	// Delete operation
	void deleteUserById(Integer id);

}
