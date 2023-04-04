package com.driver.services;


import com.driver.model.Passenger;
import com.driver.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    public Integer addPassenger(Passenger passenger){
        //Add the passenger Object in the passengerDb and return the passegnerId that has been returned
     //create object for passenger and save to repo & then return its id
       Passenger passenger1=passengerRepository.save(passenger);
    //    passengerRepository.save(passenger1);
        return passenger1.getPassengerId();
    }

}
