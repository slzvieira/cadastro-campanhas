/**
 * 
 */
package br.com.slzvieira.cadcamp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slzvieira.cadcamp.model.Campaign;
import br.com.slzvieira.cadcamp.repository.CampaignRepository;

/**
 * TODO DOCUMENT ME
 * @author sandro.vieira
 */
@Service
public class CampaignService {

    /** Quantidade de milissegundos em um dia. */
    private static final long MILLISECONDS_BY_DAY = 86_400_000;
    
    @Autowired
    CampaignRepository repository;

    /**
     * TODO DOCUMENT ME
     * @param campaign
     * @return
     */
    @Transactional
    public Campaign createCampaign(Campaign campaign) {

        List<Campaign> currentCampaignList = readActiveCampaigns();
        Campaign item = null;
        Date newEndDate = null;
        int i;
        
        /*
         * Loop 1 - Varre todas as campanhas vigentes com D+1 anteriores a nova campanha.
         * 
         * Se nova campanha nao coincide com D+1 de ninguem, entao este loop
         * eh o unico a ser processado apenas adicionano 1 a cada campanha vigente.
         */
        for (i = 0; i < currentCampaignList.size(); i++) {

            item = currentCampaignList.get(i);
            newEndDate = addDays(item.getEndDate(), 1);
            
            /* Ao encontrar uma data igual, encerra o loop de datas anteriores */
            if (newEndDate.equals(campaign.getEndDate())) {
                break;
            }

            /* Enquanto nao encontrar alguma data igual, apenas adiciona 1 */
            item.setEndDate(newEndDate);
            repository.save(item);
        }
        
        Campaign itemAnterior = item;
        i++;
        
        /*
         * Loop 2 - Varre as campanhas com D+1 posteriores a nova campanha, onde
         * devemos considerar entao o D+2.
         * 
         * Este loop eh percorrido enquanto houver campanhas com data de fim
         * sucessivas (D+2 de uma campanha = D+1 de uma outra campanha).
         * Nesta situacao, devemos sempre considerar D+2 ate encontrar um "buraco"
         * entre as datas de fim de vigencia. 
         */
        for (; i < currentCampaignList.size(); i++) {

            /* Salva o item anterior com D+2 */
            itemAnterior.setEndDate(addDays(itemAnterior.getEndDate(), 2));
            repository.save(itemAnterior);

            item = currentCampaignList.get(i);
            newEndDate = addDays(item.getEndDate(), 1);
            item.setEndDate(newEndDate);
            
            /* Ao encontrar um "buraco" (espaco entre duas datas de vencimento), aborta o segundo loop */
            if (!newEndDate.equals(itemAnterior.getEndDate())) {
                break;
            }
            
            itemAnterior = item;
        }
        
        /*
         * Loop 3 - Varre as demais campanhas apos o "buraco"
         * Nao eh mais necessario utilizar D+2.
         * Aqui voltamos a utilizar o D+1
         */
        for (; i <= currentCampaignList.size(); i++) {
            item.setEndDate(addDays(item.getEndDate(), 1));
            repository.save(item);
        }
        
        return repository.saveAndFlush(campaign);
    }

    /**
     * TODO DOCUMENT ME
     * @param id
     * @return
     */
    public Campaign read(String id) {
        return repository.findOne(id);
    }

    /**
     * TODO DOCUMENT ME
     * @return
     */
    public List<Campaign> readActiveCampaigns() {
        return repository.findByInterval(trunc(new Date()));
    }

    /**
     * TODO DOCUMENT ME
     * @param campaign
     * @return
     */
    public Campaign updateCampaign(Campaign campaign) {
        
        if (!repository.exists(campaign.getTeamId())) {
            return null;
        } else {
            return repository.save(campaign);
        }
    }

    /**
     * TODO DOCUMENT ME
     * @param id
     * @return
     */
    public Campaign deleteCampaign(String id) {
        
        Campaign oldCampaign = repository.findOne(id);

        if (oldCampaign == null) {
            return null;
        } else {
            repository.delete(id);
            return oldCampaign;
        }
    }
    
    private Date trunc(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date addDays(Date date, int days) {
        return new Date(date.getTime() + MILLISECONDS_BY_DAY * days);
    }
}
