package com.arquitectura.labs.sprint01.dto;

import com.arquitectura.labs.sprint01.model.Producto;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ProductoResponseDTO extends ResponseDTO {

    private String mensaje;
    private Date fechaEjecucion;
    private Producto producto;
}
