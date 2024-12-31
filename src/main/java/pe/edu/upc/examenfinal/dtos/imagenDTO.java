package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class imagenDTO {
    private BigInteger id;
    private String direccion;
    private BigDecimal notaid;
}
