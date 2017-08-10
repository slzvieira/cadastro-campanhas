/**
 * 
 */
package br.com.slzvieira.cadcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slzvieira.cadcamp.repository.UserRepository;

/**
 * @author sandro.vieira
 *
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository repository;

}
