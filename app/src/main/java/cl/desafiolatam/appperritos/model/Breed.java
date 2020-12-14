package cl.desafiolatam.appperritos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Breed {

    List<String> message;
    String status ;

    public Breed(List<String> message, String status) {
        this.message = message;
        this.status = status;
    }

    public Breed(){

    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BreedList{" +
                "message=" + message +
                ", status='" + status + '\'' +
                '}';
    }
}
