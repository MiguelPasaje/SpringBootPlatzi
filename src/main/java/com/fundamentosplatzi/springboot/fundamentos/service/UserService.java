package com.fundamentosplatzi.springboot.fundamentos.service;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //metodo para guardar informacion
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream().peek(user -> LOG.info("user insertado " + user))
                .forEach(userRepository::save);
                //.forEach(user -> userRepository.save(user));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();

    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }


    public User update(User newUSer, Long id) {
        return
                userRepository.findById(id).map(
                        user -> {
                            user.setEmail(newUSer.getEmail());
                            user.setBirthday(newUSer.getBirthday());
                            user.setName(newUSer.getName());
                            return userRepository.save(user);
                        }
                ).get();

    }
}
