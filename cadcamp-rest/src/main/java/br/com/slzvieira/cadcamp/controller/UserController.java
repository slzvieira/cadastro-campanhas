/**
 * 
 */
package br.com.slzvieira.cadcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.slzvieira.cadcamp.model.User;
import br.com.slzvieira.cadcamp.service.UserService;

/**
 * TODO DOCUMENT ME
 * @author sandro.vieira
 */
@RestController
public class UserController {

    @Autowired
    UserService service;

    /**
     * TODO DOCUMENT ME
     * @param campaign
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        User newUser = service.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * TODO DOCUMENT ME
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{email:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> read(@PathVariable String email) {
        User user = service.readUser(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * TODO DOCUMENT ME
     * @param campaign
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@RequestBody User user) {
        User newUser = service.updateUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    /**
     * TODO DOCUMENT ME
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {

        User foundUser = service.deleteUser(id);

        if (foundUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
    }
}
