package com.upc.ninera360.repositories;

import com.upc.ninera360.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    public long count();
    public List<Chat> findByCliente_IdCliente(Long idCliente);
}
