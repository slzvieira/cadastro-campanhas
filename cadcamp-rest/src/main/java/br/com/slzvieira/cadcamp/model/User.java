/**
 * 
 */
package br.com.slzvieira.cadcamp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Representa um dos usuario a ser cadastrado pela aplicacao.
 * @author sandro.vieira
 */
@EntityScan
@Entity
public class User {

    /** Nome Completo */
    private String completeName;

    /** E-mail */
    @Id
    private String email;

    /** Data de Nascimento */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /** Meu Time do Coracao */
    private String teamId;

    /**
     * @return the completeName
     */
    public String getCompleteName() {
        return completeName;
    }

    /**
     * @param completeName the completeName to set
     */
    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the teamId
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * @param teamId the teamId to set
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
