/**
 * 
 */
package br.com.slzvieira.cadcamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.slzvieira.cadcamp.model.Campaign;
import br.com.slzvieira.cadcamp.service.CampaignService;

/**
 * TODO DOCUMENT ME
 * @author sandro.vieira
 */
@RestController
public class CampaignController {

    @Autowired
    CampaignService service;

    /**
     * TODO DOCUMENT ME
     * @param campaign
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/campaigns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campaign> create(@RequestBody Campaign campaign) {
        Campaign newCampaign = service.createCampaign(campaign);
        return new ResponseEntity<>(newCampaign, HttpStatus.CREATED);
    }

    /**
     * TODO DOCUMENT ME
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/campaigns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Campaign>> read() {
        List<Campaign> campaignList = service.readActiveCampaigns();
        return new ResponseEntity<>(campaignList, HttpStatus.OK);
    }

    /**
     * TODO DOCUMENT ME
     * @param campaign
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/campaigns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campaign> update(@RequestBody Campaign campaign) {
        Campaign newCampaign = service.updateCampaign(campaign);
        return new ResponseEntity<>(newCampaign, HttpStatus.OK);
    }

    /**
     * TODO DOCUMENT ME
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/campaigns/{id}")
    public ResponseEntity<Campaign> delete(@PathVariable String id) {

        Campaign foundCampaign = service.deleteCampaign(id);

        if (foundCampaign == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(foundCampaign, HttpStatus.OK);
        }
    }
}
