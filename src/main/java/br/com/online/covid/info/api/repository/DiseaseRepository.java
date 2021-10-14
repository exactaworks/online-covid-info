package br.com.online.covid.info.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.online.covid.info.api.entity.CovidEntity;

@Repository
public interface DiseaseRepository extends JpaRepository<CovidEntity, String> {

    @Query(value = "SELECT * FROM covid c WHERE FORMATDATETIME(date, 'yyyy-MM-dd') = ?1 AND country = ?2", nativeQuery = true)
    List<CovidEntity> findByDateAndCountry(LocalDate date, String country);

    List<CovidEntity> deleteByCountry(String country);
    
}
