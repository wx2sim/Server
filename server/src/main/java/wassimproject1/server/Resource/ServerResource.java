package wassimproject1.server.Resource;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wassimproject1.server.Service.implementation.ServerServiceImp;
import wassimproject1.server.enumeration.Status;
import wassimproject1.server.model.Response;
import wassimproject1.server.model.Server;

import javax.activation.FileDataSource;
import javax.validation.Valid;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImp serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.list(30)))
                        .message("servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()

        );
    }

    @GetMapping("/Ping/{ipAddress}")
    public ResponseEntity<Response> pingserver(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()

        );

    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveserver(@RequestBody @Valid Server server) throws IOException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.Create(server)))
                        .message("Server Created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()

        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getserver(@PathVariable("id") long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.get(id)))
                        .message("Server Retrievead")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()

        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> Deleteserver(@PathVariable("id") long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("Server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()

        );
    }

    @SneakyThrows
    @GetMapping(path = "/image/{Filename}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("filename") String filename ) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "Downloads/images/" + filename));
    }
}
