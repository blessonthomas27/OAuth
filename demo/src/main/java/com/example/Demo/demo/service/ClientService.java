package com.example.Demo.demo.service;

import com.example.Demo.demo.entity.Client;
import com.example.Demo.demo.repository.ClientRepository;
import com.example.Demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(Long clientId){
        try{
           Client client= clientRepository.findById(clientId).get();
           if (client!=null){
               return client;
           }
           return null;
        }catch (Exception e) {
            return null;
        }
    }

}
