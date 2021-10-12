package br.com.online.covid.info.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import br.com.online.covid.info.api.entity.CovidEntity;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class DiseaseRepository extends BaseRepository {

    public DiseaseRepository(Jdbi jdbi) {
        super(jdbi);
    }

    public Optional<CovidEntity> save(CovidEntity entity) {

        entity.setId(UUID.randomUUID().toString());

        try {
            jdbi.inTransaction(handle ->
                handle.createUpdate(sqlLocator.locate("db.sql.save-covid"))
                    .bindBean(entity)
                    .execute());
            return Optional.of(entity);
        }catch(Exception e) {
            log.error(String.format("save method error: %s", e.getLocalizedMessage()));
        }

        return Optional.empty();
    }

}
