package co.edu.uniquindio.airline.dto;

public class ResponseDTO {
    private String respuesta;

    // Constructor
    public ResponseDTO(String respuesta) {
        this.respuesta = respuesta;
    }

    // Getter
    public String getRespuesta() {
        return respuesta;
    }

    // Setter
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
