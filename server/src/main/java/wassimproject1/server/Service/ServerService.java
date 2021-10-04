package wassimproject1.server.Service;

import wassimproject1.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server Create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
