package Coop.Sut.service;

import Coop.Sut.model.User;
import Coop.Sut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public  User findUserByEmailandPassword(String email,String password){
        User u = userRepository.findUser(email,password);
        return u;
    }
   /* public void insertOfUser(User user){
        userRepository.insertUser(user);
    }*/

}
