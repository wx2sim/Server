package wassimproject1.server.Service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wassimproject1.server.Repos.ServerRepos;
import wassimproject1.server.Service.ServerService;
import wassimproject1.server.enumeration.Status;
import wassimproject1.server.model.Server;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImp implements ServerService {
private  final ServerRepos serverRepos;
    @Override
    public Server Create(Server server) {
        log.info("saving new Server {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepos.save(server);

    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging Server Ip : {}",ipAddress);
        Server server = serverRepos.findbyipAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepos.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers: {}");
        return serverRepos.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server By id: {}", id);
        return serverRepos.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server {}",server.getName());
        return serverRepos.save(server);

    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting Server by id{}",id);
        serverRepos.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl () {
        String[] imagenames = { "server1.png" ,"server2.png" ,"server3.png" ,"server4.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/images/" + imagenames[new Random().nextInt(4)]).toUriString();
    }
}
