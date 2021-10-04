package wassimproject1.server.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import wassimproject1.server.model.Server;

public interface ServerRepos extends JpaRepository<Server, Long>{
    Server findbyipAddress(String ipAddress);

}
