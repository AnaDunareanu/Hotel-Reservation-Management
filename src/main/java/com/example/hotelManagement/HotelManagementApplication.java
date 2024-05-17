package com.example.hotelManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.hotelManagement.Service.dataLoaderService;

@SpringBootApplication
public class HotelManagementApplication implements CommandLineRunner {

	@Autowired
	private dataLoaderService dataLoaderService;
	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataLoaderService.loadHotelsFromJson();
	}

}
