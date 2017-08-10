/**
 * 
 */
package br.com.slzvieira.cadcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.slzvieira.cadcamp.model.User;

/**
 * TODO DOCUMENT ME
 * @author sandro.vieira
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
