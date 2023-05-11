package dk.a2mate.palletizing.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletRepository 
extends JpaRepository<PalletDao, String> {}
