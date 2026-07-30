package com.example.demo;

import com.example.demo.repository.ConfigJournalAppRepository;
import com.example.demo.repository.JournalEntryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(DemoApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		System.out.println(environment.getActiveProfiles()[0]);
	}
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
	@Bean
	public RestTemplate restTemplate(){  //updated version uses RestClient
		return new RestTemplate();
	}
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JournalEntryRepository journalEntryRepository;

	@Autowired
	private ConfigJournalAppRepository configJournalAppRepository;

	@Autowired
	MongoDatabaseFactory factory;

	@Bean
	CommandLineRunner test(Environment env) {
		return args -> {
			System.out.println("URI = " + env.getProperty("spring.mongodb.uri"));
			System.out.println("Database Property = " + env.getProperty("spring.data.mongodb.database"));
			System.out.println("Factory DB = " + factory.getMongoDatabase().getName());
		};
	}
}
