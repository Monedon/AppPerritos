package cl.desafiolatam.appperritos.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.desafiolatam.appperritos.api.RetroFitClient;
import cl.desafiolatam.appperritos.model.Breed;
import cl.desafiolatam.appperritos.model.BreedImage;
import cl.desafiolatam.appperritos.model.Favorite;
import cl.desafiolatam.appperritos.view.Adapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    private static final String TAG = "Adapter";
    IPresenterView view;
    Adapter adapter;
    List<String> breedList;
    List<String> breedImages;
    RetroFitClient retroFitClient;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public Presenter(IPresenterView pView) {
        this.view = pView;
        retroFitClient = new RetroFitClient();
        breedList = new ArrayList<>();
        loadInfo();

    }

    public List<String> loadImages(String string) {
        breedImages = new ArrayList<>();
        Log.d(TAG, "loadInfo: Ocupando loadinfo");
        RetroFitClient.getRetrofitInstance().getImages(string).enqueue(new Callback<BreedImage>() {

            @Override
            public void onResponse(Call<BreedImage> call, Response<BreedImage> response) {
                breedImages.addAll(response.body().getMessage());
                Log.d(TAG, "onResponse: Conectó a la API " + breedImages.size());
                view.showBreedImages(breedImages);
            }

            @Override
            public void onFailure(Call<BreedImage> call, Throwable t) {
                Log.d(TAG, "onFailure: Falló" + t.toString());

            }
        });
        return breedImages;
    }



    public List<String> loadInfo() {
        Log.d(TAG, "loadInfo: Ocupando loadinfo");
        RetroFitClient.getRetrofitInstance().getAllBreeds().enqueue(new Callback<Breed>() {

            @Override
            public void onResponse(Call<Breed> call, Response<Breed> response) {
                breedList.addAll(response.body().getMessage());
                Log.d(TAG, "onResponse: Conectó a la API " + breedList);
                view.showBreedList(breedList);
            }

            @Override
            public void onFailure(Call<Breed> call, Throwable t) {
                Log.d(TAG, "onFailure: Falló" + t.toString());

            }
        });
        return breedList;
    }

    public void setFireStoreData(String name, String image){
        Map<String, Object> fav = new HashMap<>();
        fav.put("breed", name);
        fav.put("image", image);
        fav.put("timestamp", new Date().toString());
        db.collection("favorites")
                .add(fav)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: FIRESTORE UPDATED");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " +e);
            }
        });
        }

    public List<Favorite> readData() {
        List<Favorite> listFavorites=new ArrayList<>();
        db.collection("favorites")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Favorite favorite = setFavorite(document);
                                listFavorites.add(favorite);
                                view.showBreedFavorites(listFavorites);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        Log.d(TAG, "readData: list " + listFavorites);
            return listFavorites;
        }

    private Favorite setFavorite(QueryDocumentSnapshot document){
        Favorite favorite = new Favorite();
        favorite.setBreed(document.getString("breed"));
        favorite.setImageURI(document.getString("image"));
        favorite.setTimesStamp(document.getString("timestamp"));
        return favorite;
    }

    }

