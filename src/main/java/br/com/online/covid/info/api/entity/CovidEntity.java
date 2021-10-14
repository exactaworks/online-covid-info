package br.com.online.covid.info.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "covid")
@Data
public class CovidEntity {

    @Id
    @Column
    private String id;

    @Column
    private LocalDateTime date;

    @Column
    private String country;

    @Column
    private Integer cases;

    @Column
    private Integer death;

    @Column
    private Integer recovered;

    @Column
    private Long population;
}
