package kz.tdcis.poe.democlient.repository;

import kz.tdcis.poe.democlient.model.ClientData;
import kz.tdcis.poe.democlient.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ClientDataRepository extends JpaRepository<ClientData, Long> {
     Optional<ClientData> findByIdAndDate(Long id, LocalDateTime date);

     @Modifying
     @Query("update ClientData clientData set clientData.hash = ?1, clientData.referenceHash = ?1 where clientData.id = ?2")
     int setHashToClientData(String hash, Long id);

}
