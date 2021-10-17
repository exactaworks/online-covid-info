package br.com.online.covid.info.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * ContinentCovidEntity
 */
@Entity
@Table(name = "continent")
@Data
public class ContinentCovidEntity {

    @Id
    @Column
    private String id;

    @Column
    private LocalDateTime date;

    @Column
    private String continent;

    @Column
    private Integer cases;
   
    @Column
    private Integer deaths;

    @Column
    private Integer recovered;

    @Column
    private Integer population;

}