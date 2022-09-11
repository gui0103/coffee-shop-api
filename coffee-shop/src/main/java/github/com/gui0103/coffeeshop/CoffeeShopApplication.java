package github.com.gui0103.coffeeshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CoffeeShopApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
