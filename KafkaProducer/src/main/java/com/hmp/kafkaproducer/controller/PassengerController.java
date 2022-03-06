
/*
 * Copyright (c) 2022 : 2/27/22, 5:52 PM
 * Last Modified: 2/27/22, 5:39 PM
 * All Rights Reserved: hobject
 *
 *
 */

package com.hmp.kafkaproducer.controller;

import com.hmp.kafkaproducer.entity.Passenger;
import com.hmp.kafkaproducer.repository.PassengerRepository;
import com.hmp.kafkaproducer.service.PassengerService;

import com.hmp.kafkaproducer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/")
public class PassengerController {
	private final PassengerRepository passengerRepository;
	private final Passenger passenger;
	private final PassengerService passengerService;
	private final ProducerService producerService;
	@Autowired
	public PassengerController(PassengerRepository passengerRepository, Passenger passenger, PassengerService passengerService, ProducerService producerService) {
		this.passengerRepository = passengerRepository;
		this.passenger = passenger;
		this.passengerService = passengerService;
		this.producerService = producerService;
	}

	@RequestMapping("/find")
	public  List<Passenger> getForm() {

		return (List<Passenger>) passengerRepository.findAll();
	}
	@PostMapping("/addData")
	public String postData(@RequestParam String fname, @RequestParam String lname, @RequestParam String email, @RequestParam String phone, @RequestParam String deleted) {
		passenger.setFirstName(fname);
		passenger.setLastName(lname);
		passenger.setEmail(email);
		passenger.setPhone(phone);
		passenger.setDeleted(deleted);
		passengerRepository.save(passenger);
		return "Successfully saved!";
	}

	//kafka send message
	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("id") int id) {
		Passenger passenger =passengerRepository.findById(id);
		this.producerService.sendMessage(passenger);
	}


	}
