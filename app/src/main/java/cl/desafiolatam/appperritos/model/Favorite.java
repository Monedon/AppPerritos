package cl.desafiolatam.appperritos.model;

public class Favorite {

    private String breed;
    private String imageURI;

    public Favorite(String breed, String imageURI) {
        this.breed = breed;
        this.imageURI = imageURI;
    }

    public Favorite() {

    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "breed='" + breed + '\'' +
                ", imageURI='" + imageURI + '\'' +
                '}';
    }
}
