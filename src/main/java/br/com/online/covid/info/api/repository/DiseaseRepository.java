package br.com.online.covid.info.api.repository;

import br.com.online.covid.info.api.entity.CovidEntity;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

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
            e.printStackTrace();
        }

        return Optional.of(new CovidEntity());
    }

}
