package com.agrestina.repository;

import com.agrestina.domain.client.Client;
import com.agrestina.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByName(String name);

    boolean existsByNameIgnoringCase(String name);
    @Query("SELECT c FROM Client c WHERE c.active=true")
    Page<Client> findActive(Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
    @Query("SELECT c FROM Client c WHERE c.active=false")
    Page<Client> findInactive(Pageable pageable);

    boolean existsByName(String name);
    @Query("SELECT c FROM Client c")
    Page<Client> findAllClients(Pageable pageable);

}
