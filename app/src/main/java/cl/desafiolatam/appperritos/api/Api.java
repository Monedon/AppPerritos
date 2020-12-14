package cl.desafiolatam.appperritos.api;

import java.util.List;

import cl.desafiolatam.appperritos.model.BreedImage;
import cl.desafiolatam.appperritos.model.Breed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("breeds/list")
    Call<Breed> getAllBreeds();
    @GET("breed/{breed}/images")
    Call<BreedImage> getImages(@Path("breed")String breed);
}
