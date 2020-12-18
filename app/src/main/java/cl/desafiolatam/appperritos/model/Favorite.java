package cl.desafiolatam.appperritos.model;

public class Favorite {

    private String breed;
    private String imageURI;
    private String timesStamp;

    public Favorite(String breed, String imageURI, String timesStamp) {
        this.breed = breed;
        this.imageURI = imageURI;
        this.timesStamp = timesStamp;
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

    public String getTimesStamp() {
        return timesStamp;
    }

    public void setTimesStamp(String timesStamp) {
        this.timesStamp = timesStamp;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "breed='" + breed + '\'' +
                ", imageURI='" + imageURI + '\'' +
                ", timesStamp='" + timesStamp + '\'' +
                '}';
    }
}
