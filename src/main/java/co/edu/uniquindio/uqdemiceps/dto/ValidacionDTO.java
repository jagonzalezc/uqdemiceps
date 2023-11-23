package co.edu.uniquindio.uqdemiceps.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidacionDTO {
    private String campo;
    private String error;
}
