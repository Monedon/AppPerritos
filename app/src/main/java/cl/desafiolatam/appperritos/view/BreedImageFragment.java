package cl.desafiolatam.appperritos.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cl.desafiolatam.appperritos.R;
import cl.desafiolatam.appperritos.model.Favorite;
import cl.desafiolatam.appperritos.presenter.IPresenterView;
import cl.desafiolatam.appperritos.presenter.Presenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BreedImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreedImageFragment extends Fragment implements IPresenterView {

    private static final String TAG = "Adapter";
    RecyclerView recyclerView;
    AdapterImage adapterImage;
    Presenter presenter = new Presenter(this);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String name;
    private String UpperCaseName = "";
    private String mParam2;

    public BreedImageFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BreedImageFragment newInstance(String param1, String param2) {
        BreedImageFragment fragment = new BreedImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breed_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        UpperCaseName = name.substring(0, 1).toUpperCase() + name.substring(1);
        TextView nameTv = view.findViewById(R.id.tvName);
        nameTv.setText(UpperCaseName);
        recyclerView = view.findViewById(R.id.imageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        final List<String> images = new Presenter(this).loadImages(name);
        adapterImage = new AdapterImage(getContext(), images);
        recyclerView.setAdapter(adapterImage);

        adapterImage.setOnItemLongClickListener(new AdapterImage.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                String image = images.get(position);
                final String tag = "images";
                presenter.setFireStoreData(name, image);
                Toast.makeText(getActivity(), "Agregado a Favoritos", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void showBreedList(List<String> list) {

    }

    @Override
    public void showBreedImages(List<String> breedList) {
        Log.d(TAG, "showBreedList: ");
        adapterImage.update(breedList);
    }

    @Override
    public void showBreedFavorites(List<Favorite> list) {

    }
}