package cl.desafiolatam.appperritos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import cl.desafiolatam.appperritos.R;
import cl.desafiolatam.appperritos.databinding.ActivityMainBinding;
import cl.desafiolatam.appperritos.model.Favorite;
import cl.desafiolatam.appperritos.presenter.IPresenterView;

/* TODO
****Requerimientos****
* 1. MVP
* 2. Consumo de API
* 3. ViewBinding.
* 4. RecyclerView.
* 5. Glide (Imagenes).
* 6. Test Unitario.
*
Lista de tareas
[ ] Crear y subir a GitHub.
[X] Adpater.
[ ] Retrofit (Retrofit Client, Api Interface (@GET)). Añadir permisos internet Manifest
[X] Activar ViewBinding e implementar().
[X] POJO
*   [ ] Lista de Raza
*   [ ] Imagenes de Raza
[ ] Layout (el o los).
*   [ ] layout lista
*   [ ] layout elemento
*   [ ] layout detalle
[ ] Implemetar Adapter y RecyclerView.
[ ] Test unitarios a modelo y ViewModel


 */

public class MainActivity extends AppCompatActivity implements IPresenterView {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportFragmentManager().beginTransaction().add(R.id.container, BreedListFragment.newInstance("", ""),"listFragment").commit();
    }

    @Override
    public void onBackPressed(){
        Fragment oldFragment = getSupportFragmentManager().findFragmentByTag("detailFr");
        if(oldFragment == null){
            super.onBackPressed();
        }else {
            getSupportFragmentManager().beginTransaction().add(R.id.container, BreedListFragment.newInstance("", ""),"listFragment")
                    .remove(oldFragment).commit();
        }
    }


    @Override
    public void showBreedList(List<String> breedList) {

    }

    @Override
    public void showBreedImages(List<String> breedList) {

    }

    @Override
    public void showBreedFavorites(List<Favorite> list) {

    }
}