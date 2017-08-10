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
 * Representa uma das campanhas gerenciadas pela aplicacao.
 * @author sandro.vieira
 */
@EntityScan
@Entity
public class Campaign {

    /** ID do Time do Coracao */
    @Id
    private String teamId;

    /** Nome Da Campanha */
    private String name;

    /** Data inicial da Vigencia */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    /** Data final da Vigencia */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    /**
     * Retorna o ID do Time do Coracao
     * @return the teamId
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * Assinala o ID do Time do Coracao
     * @param teamId the teamId to set
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * Retorna o Nome Da Campanha
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Assinala o Nome Da Campanha
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna a Data inicial da Vigencia
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Assinala a Data inicial da Vigencia
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Retorna a Data final da Vigencia
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Assinala a Data final da Vigencia
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
