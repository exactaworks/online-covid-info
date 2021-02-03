package br.com.online.covid.info.api.repository;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.locator.ClasspathSqlLocator;

public abstract class BaseRepository {

    public final Jdbi jdbi;
    public final ClasspathSqlLocator sqlLocator;

    public BaseRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.sqlLocator = ClasspathSqlLocator.create();
    }




}
