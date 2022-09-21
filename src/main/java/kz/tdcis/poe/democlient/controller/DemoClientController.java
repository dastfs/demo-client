package kz.tdcis.poe.democlient.controller;

import kz.tdcis.poe.democlient.dto.ClientDataDto;
import kz.tdcis.poe.democlient.dto.client.ClientHashList;
import kz.tdcis.poe.democlient.dto.client.ClientHashResList;
import kz.tdcis.poe.democlient.service.ClientExternalApi;
import kz.tdcis.poe.democlient.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/demo")
@RequiredArgsConstructor
public class DemoClientController {

    private final ClientService clientService;
    private final ClientExternalApi clientExternalService;

    @PostMapping
    public ResponseEntity<ClientDataDto> saveData(@RequestBody ClientDataDto data) {
        return ResponseEntity.ok(clientService.createClientData(data));
    }

    @PutMapping
    public ResponseEntity<ClientDataDto> changeData(@RequestBody ClientDataDto data){
        return ResponseEntity.ok(clientService.updateClientData(data));
    }

    @GetMapping
    public ResponseEntity<List<ClientDataDto>> getAllClientData(){
        return ResponseEntity.ok(clientService.getAll());
    }

    @PostMapping("/hashes")
    public ResponseEntity<ClientHashResList> getHashesFromClient(@RequestBody ClientHashList requestBody) throws Exception {
        return ResponseEntity.ok(clientExternalService.getHashesFromClient(requestBody));
    }

}
