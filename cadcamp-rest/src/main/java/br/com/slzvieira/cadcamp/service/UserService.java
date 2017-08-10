/**
 * 
 */
package br.com.slzvieira.cadcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slzvieira.cadcamp.model.User;
import br.com.slzvieira.cadcamp.repository.UserRepository;

/**
 * @author sandro.vieira
 *
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository repository;

    /**
     * TODO DOCUMENT ME
     * @param user
     * @return
     */
    public User createUser(User user) {
        return repository.saveAndFlush(user);
    }

    /**
     * TODO DOCUMENT ME
     * @param email
     * @return
     */
    public User readUser(String email) {
        return repository.findOne(email);
    }

    /**
     * TODO DOCUMENT ME
     * @param user
     * @return
     */
    public User updateUser(User user) {
        
        if (!repository.exists(user.getEmail())) {
            return null;
        } else {
            return repository.save(user);
        }
    }

    /**
     * TODO DOCUMENT ME
     * @param id
     * @return
     */
    public User deleteUser(String email) {
        
        User oldUser = repository.findOne(email);

        if (oldUser == null) {
            return null;
        } else {
            repository.delete(email);
            return oldUser;
        }
    }
}
