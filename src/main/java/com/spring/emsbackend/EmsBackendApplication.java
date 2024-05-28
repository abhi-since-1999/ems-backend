package com.spring.emsbackend;

import com.spring.emsbackend.entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
	}
	public void fun(){
		Employee e = new Employee();


	}
}
