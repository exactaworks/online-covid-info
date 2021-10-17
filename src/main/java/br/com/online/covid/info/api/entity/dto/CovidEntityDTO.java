package br.com.online.covid.info.api.entity.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CovidEntityDTO {

    @Size(min=2)
    @NotBlank(message = "Country is mandatory")
    private String country;

    @Min(0)
    @NotNull(message = "Cases are mandatory")
    private Integer cases;

    @Min(0)
    @NotNull(message = "Death is mandatory")
    private Integer death;

    @Min(0)
    @NotNull(message = "Recovered is mandatory")
    private Integer recovered;
    
    @Min(0)
    @NotNull(message = "Population is mandatory")
    private Long population;

}
