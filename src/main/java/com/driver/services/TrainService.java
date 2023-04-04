package com.driver.services;

import com.driver.EntryDto.AddTrainEntryDto;
import com.driver.EntryDto.SeatAvailabilityEntryDto;
import com.driver.model.Passenger;
import com.driver.model.Station;
import com.driver.model.Ticket;
import com.driver.model.Train;
import com.driver.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;

    public Integer addTrain(AddTrainEntryDto trainEntryDto){

        //Add the train to the trainRepository
        //and route String logic to be taken from the Problem statement.

        //first get the list of station for particular train from dto
        List<Station> stationList=trainEntryDto.getStationRoute();
        //now set route for stations ,Logic given in problem ie:"A,B,C"
        StringBuilder route= new StringBuilder();
        for(Station station : stationList){            //make route for all stations
            if(route.length()==0){                    //if route length for station zero then just append its name
                route.append(station.toString());
            }
            else{                                         //append comma with space with station name
               route.append(", ").append(station.toString());
            }
        }
        //Save the train and return the trainId that is generated from the database.
        //Avoid using the lombok library
        //now set departuretime,route,seats for a train from dto by creating its object
        Train train=new Train();
        train.setNoOfSeats(trainEntryDto.getNoOfSeats());
        train.setDepartureTime(trainEntryDto.getDepartureTime());
        train.setRoute(route.toString());

        //now save this train object in repo & return its id

        Train train1=trainRepository.save(train);

        return train1.getTrainId();
    }

    public Integer calculateAvailableSeats(SeatAvailabilityEntryDto seatAvailabilityEntryDto){

        //Calculate the total seats available
        //Suppose the route is A B C D
        //And there are 2 seats avaialble in total in the train
        //and 2 tickets are booked from A to C and B to D.
        //The seat is available only between A to C and A to B. If a seat is empty between 2 station it will be counted to our final ans
        //even if that seat is booked post the destStation or before the boardingStation
        //Inshort : a train has totalNo of seats and there are tickets from and to different locations
        //We need to find out the available seats between the given 2 stations.

        //firstly get the fromstation , tostation & trainid(int) for partucal train from dto
        Station fromStation = seatAvailabilityEntryDto.getFromStation();
        Station toStation = seatAvailabilityEntryDto.getToStation();
        int trainid=seatAvailabilityEntryDto.getTrainId();
        Train train=trainRepository.findById(trainid).get();  //now get train for that trainId & get noofseats from repo
        int noOfSeats=train.getNoOfSeats();
        List<Ticket>listofTickets=train.getBookedTickets();  //now get list of tickets for train from bookedticketslist
         int countofbookedticketbetStaion=0;                //intially booked tickets zero
        for(Ticket ticket : listofTickets){                  //check evry ticket in list fromstation to tostation &add passengerlist size into count
            if(ticket.getFromStation().equals(fromStation) && ticket.getToStation().equals(toStation)){
                countofbookedticketbetStaion+=ticket.getPassengersList().size();
            }
        }
         //now calculate available seats
        //ie: total seats - countofbookedtickets
        int availableSeats=noOfSeats-countofbookedticketbetStaion;

        return availableSeats;
    }

    public Integer calculatePeopleBoardingAtAStation(Integer trainId,Station station) throws Exception{

        //We need to find out the number of people who will be boarding a train from a particular station
        //if the trainId is not passing through that station
        //throw new Exception("Train is not passing from this station");
        //  in a happy case we need to find out the number of such people.


        return 0;
    }

    public Integer calculateOldestPersonTravelling(Integer trainId){

        //Throughout the journey of the train between any 2 stations
        //We need to find out the age of the oldest person that is travelling the train
        //If there are no people travelling in that train you can return 0

        return 0;
    }

    public List<Integer> trainsBetweenAGivenTime(Station station, LocalTime startTime, LocalTime endTime){

        //When you are at a particular station you need to find out the number of trains that will pass through a given station
        //between a particular time frame both start time and end time included.
        //You can assume that the date change doesn't need to be done ie the travel will certainly happen with the same date (More details
        //in problem statement)
        //You can also assume the seconds and milli seconds value will be 0 in a LocalTime format.

        return null;
    }

}
