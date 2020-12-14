package cl.desafiolatam.appperritos.presenter;

import java.util.List;

import cl.desafiolatam.appperritos.model.Favorite;

public interface IPresenterView {

    void showBreedList(List<String> list);

    void showBreedImages(List<String> breedList);

    void showBreedFavorites(List<Favorite> list);
}
