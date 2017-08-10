/**
 * 
 */
package br.com.slzvieira.cadcamp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.slzvieira.cadcamp.model.Campaign;

/**
 * TODO DOCUMENT ME
 * @author sandro.vieira
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String> {

    /**
     * Obtem a lista de campanhas atuais ordenadas por data final de vigencia
     * @param currentDate
     * @return Lista de campanhas vigentes
     */
    @Query("SELECT c FROM Campaign c WHERE ?1 BETWEEN c.startDate AND c.endDate ORDER BY c.endDate")
    List<Campaign> findByInterval(Date currentDate);
}
