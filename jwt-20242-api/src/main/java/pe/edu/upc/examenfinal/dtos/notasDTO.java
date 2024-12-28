package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class notasDTO {
    private BigInteger id;
    private String titulo;
    private String descripcion;
    private String username;
}
