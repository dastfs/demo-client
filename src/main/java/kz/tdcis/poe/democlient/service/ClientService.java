package kz.tdcis.poe.democlient.service;

import kz.tdcis.poe.democlient.dto.ClientDataDto;
import kz.tdcis.poe.democlient.dto.poeHash.AddProofResponse;
import kz.tdcis.poe.democlient.model.ClientData;
import kz.tdcis.poe.democlient.model.Status;
import kz.tdcis.poe.democlient.repository.ClientDataRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientDataRepository clientDataRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;

    @Transactional
    public ClientDataDto createClientData(ClientDataDto data) {
        ClientData clientData = ClientData.createDataWithStatus(Status.NOT_VERIFIED, data);
        clientData = clientDataRepository.save(clientData);

        AddProofResponse hashResponse = hashService.add(clientData.getId(), clientData.getData());
        clientDataRepository.setHashToClientData(hashResponse.getProof(), clientData.getId());

        return modelMapper.map(clientData, ClientDataDto.class);
    }

    @Transactional
    public ClientDataDto updateClientData(ClientDataDto data) {

        ClientData clientData = clientDataRepository.findByIdAndDate(data.getId(), data.getDate()).orElseThrow(NoSuchElementException::new);
        clientData.setData(data.getData());

        String hash = DigestUtils.sha256Hex(clientData.getData());
        clientData.setHash(hash);

        ClientData clientDataRes = clientDataRepository.save(clientData);
        return modelMapper.map(clientDataRepository.save(clientDataRes), ClientDataDto.class);
    }

    public List<ClientDataDto> getAll(){
        List<ClientData> clientDataList = clientDataRepository.findAll();
        return modelMapper.map(clientDataList, new TypeToken<List<ClientDataDto>>() {}.getType());
    }

    @Transactional
    public String setStatusForClient(Long id, String hashFromBC) {
        ClientData clientData = clientDataRepository.findById(id).orElse(null);
        if(clientData == null){
            return hashFromBC;
        }

        String hash = clientData.getHash();
        clientData.setStatusCheckDate(LocalDateTime.now());

        if (hashFromBC.equals(hash)){
            clientData.setStatus(Status.VALID);
        }else{
            clientData.setStatus(Status.INVALID);
        }

        clientDataRepository.save(clientData);
        return hash;
    }

}
