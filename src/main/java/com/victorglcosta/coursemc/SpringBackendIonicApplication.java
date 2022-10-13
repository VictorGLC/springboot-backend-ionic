package com.victorglcosta.coursemc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorglcosta.coursemc.domain.Address;
import com.victorglcosta.coursemc.domain.Category;
import com.victorglcosta.coursemc.domain.City;
import com.victorglcosta.coursemc.domain.Customer;
import com.victorglcosta.coursemc.domain.Order;
import com.victorglcosta.coursemc.domain.OrderItem;
import com.victorglcosta.coursemc.domain.Payment;
import com.victorglcosta.coursemc.domain.PaymentByCard;
import com.victorglcosta.coursemc.domain.PaymentBySlip;
import com.victorglcosta.coursemc.domain.Product;
import com.victorglcosta.coursemc.domain.State;
import com.victorglcosta.coursemc.domain.enums.CustomerType;
import com.victorglcosta.coursemc.domain.enums.PaymentStatus;
import com.victorglcosta.coursemc.repositories.AddressRepository;
import com.victorglcosta.coursemc.repositories.CategoryRepository;
import com.victorglcosta.coursemc.repositories.CityRepository;
import com.victorglcosta.coursemc.repositories.CustomerRepository;
import com.victorglcosta.coursemc.repositories.OrderItemRepository;
import com.victorglcosta.coursemc.repositories.OrderRepository;
import com.victorglcosta.coursemc.repositories.PaymentRepository;
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
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
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
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cust1, a1);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cust1, a2);
		
		Payment pay1 = new PaymentByCard(null, PaymentStatus.PAID, ord1, 6);
		ord1.setPayment(pay1);
		
		Payment pay2 = new PaymentBySlip(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(pay2);
		
		cust1.getOrders().addAll(Arrays.asList(ord1, ord2));
	
		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderItem oi1 = new OrderItem(ord1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(ord1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(ord2, p2, 100.00, 1, 800.00);
	
		ord1.getItems().addAll(Arrays.asList(oi1, oi2));
		ord2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		
		orderItemRepository.saveAll((Arrays.asList(oi1, oi2, oi3)));
	}

}
