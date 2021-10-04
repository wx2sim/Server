package wassimproject1.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wassimproject1.server.Repos.ServerRepos;
import wassimproject1.server.enumeration.Status;
import wassimproject1.server.model.Server;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	CommandLineRunner run(ServerRepos serverRepos){
		return args -> {
		serverRepos.save(new Server(null,"192.168.1.160","Redhat linux","16 gb","Personal",
				"http://localhost:8080/Server/images/server1.png", Status.SERVER_UP));
		serverRepos.save(new Server(null,"192.168.1.168","Kali linux","4 gb","work",
					"http://localhost:8080/Server/images/server2.png", Status.SERVER_DOWN));
		serverRepos.save(new Server(null,"192.168.1.162","Windows Server","32 gb","Personal computer",
					"http://localhost:8080/Server/images/server3.png", Status.SERVER_UP));
		serverRepos.save(new Server(null,"192.168.1.165","Home Server","8 gb","home",
					"http://localhost:8080/Server/images/server4.png", Status.SERVER_DOWN));



		};

	}

}
