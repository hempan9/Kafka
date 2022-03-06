package com.hmp.kafkaproducer.service;
import com.hmp.kafkaproducer.entity.Passenger;
import com.hmp.kafkaproducer.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Autowired
    public PassengerService(PassengerRepository repository){
        this.repository= repository;
    }
    PassengerRepository repository;

    public Page<Passenger> pageByALl(int page, int offset, String sortby) {
        Pageable passengers;
        Page<Passenger> passengersPage = null;
        try {
            passengers = PageRequest.of(page, offset, Sort.by(sortby));
           passengersPage = repository.findAll(passengers);
        }catch (Exception e)
        {
            e.getMessage();
        }
        return passengersPage;
    }

    public void deleteSoftDelete(List<Integer> ids) {
        repository.deleteAllById(ids);
    }

    public void deleteSoftDelete(Integer id) {
        repository.deleteById(id);

    }
    public void deleteSoftDelete( ) {
        repository.deleteAll();

    }

}
