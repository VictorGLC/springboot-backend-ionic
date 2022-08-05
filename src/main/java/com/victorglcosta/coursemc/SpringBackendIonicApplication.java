package com.victorglcosta.coursemc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorglcosta.coursemc.domain.Address;
import com.victorglcosta.coursemc.domain.Category;
import com.victorglcosta.coursemc.domain.City;
import com.victorglcosta.coursemc.domain.Customer;
import com.victorglcosta.coursemc.domain.Product;
import com.victorglcosta.coursemc.domain.State;
import com.victorglcosta.coursemc.domain.enums.CustomerType;
import com.victorglcosta.coursemc.repositories.AddressRepository;
import com.victorglcosta.coursemc.repositories.CategoryRepository;
import com.victorglcosta.coursemc.repositories.CityRepository;
import com.victorglcosta.coursemc.repositories.CustomerRepository;
import com.victorglcosta.coursemc.repositories.ProductRepository;
import com.victorglcosta.coursemc.repositories.StateRepository;

@SpringBootApplication
public class SpringBackendIonicApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBackendIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
	
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Customer cust1 = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.NATURALPERSON);
		
		cust1.getTelephones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address a1 = new Address(null, "Rua flores", "300", "Apto 203", "Jardim", "38220834", cust1, c1);
		Address a2 = new Address(null, "Avenida 105", "105", "Sala 800", "Centro", "38777012", cust1, c2);
		
		cust1.getAddress().addAll(Arrays.asList(a1, a2));
		
		customerRepository.saveAll(Arrays.asList(cust1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
	}

}
