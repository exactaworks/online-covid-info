package br.com.online.covid.info.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.online.covid.info.api.entity.ContinentCovidEntity;

public interface ContinentCovidEntityRepository extends JpaRepository<ContinentCovidEntity, String> {
    
}
