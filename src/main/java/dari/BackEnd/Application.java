package dari.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dari.BackEnd.services.IDariInitService;

@SpringBootApplication
public class Application/* implements CommandLineRunner*/ {
	@Autowired
   private IDariInitService dariInitService;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//@Override
	/*public void run(String... args) throws Exception {
		dariInitService.initAdmins();
		dariInitService.initLocateurs();
		dariInitService.initProps();
		dariInitService.initLogements();
		dariInitService.initReservs();
		
	}*/

}
