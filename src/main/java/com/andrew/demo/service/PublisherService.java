package com.andrew.demo.service;

import com.andrew.demo.DAO.PublisherDAO;
import com.andrew.demo.model.Publisher;
import com.andrew.demo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
import java.util.UUID;

@Service
public class PublisherService {
    @Autowired
    private PublisherDAO publisherDAO;

    public PublisherService() {
    }

    public boolean findPubliser(UUID publisherId){
        return publisherDAO.getPublisherCount(publisherId) > 0;
    }

    public Publisher getPublisher(String publisherName, UUID publisherId){

        return publisherId==null?publisherDAO.findByName(publisherName) : publisherDAO.findById(publisherId).get();
    }

    public void addPulbisher(Publisher publisher){
        if(publisher.getPublisherId() == null){
            publisher.setPublisherId(Utility.generateUUID());
        }
        publisherDAO.save(publisher);
    }
}
