package cl.desafiolatam.appperritos.view;

import android.graphics.Color;
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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import cl.desafiolatam.appperritos.R;
import cl.desafiolatam.appperritos.api.RetroFitClient;
import cl.desafiolatam.appperritos.model.Breed;
import cl.desafiolatam.appperritos.model.Favorite;
import cl.desafiolatam.appperritos.presenter.IPresenterView;
import cl.desafiolatam.appperritos.presenter.Presenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BreedListFragment extends Fragment implements IPresenterView {

    private static final String TAG = "Adapter";
    Presenter presenter;
    Breed breedList;
    List<String> list = new ArrayList<>();
    Button button;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BreedListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BreedListFragment newInstance(String param1, String param2) {
        BreedListFragment fragment = new BreedListFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Adapter adapter;
    AdapterFirebase adapterFirebase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_breed_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: ");
        presenter = new Presenter(this);
        RecyclerView recyclerView = view.findViewById(R.id.breedRecycler);
        // Inflate the layout for this fragment
        adapter = new Adapter(getContext(), new ArrayList<>());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        final List<String> list = new Presenter(this).loadInfo();
        Log.d(TAG, "onViewCreated: IMAGES" + list.size());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String breed = list.get(position);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final String tag = "detailFr";
                fragmentManager.beginTransaction().add(R.id.container, BreedImageFragment.newInstance(breed,""),tag)
                .addToBackStack(tag).remove(fragmentManager.findFragmentByTag("listFragment")).commit();
            }
        });
        List<Favorite> getData = presenter.readData();
        adapterFirebase = new AdapterFirebase(getContext(),getData);
        button = view.findViewById(R.id.btnFavorites);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final String tag = "detailFr";
                fragmentManager.beginTransaction().add(R.id.container, FireBaseFragment.newInstance("",""),tag)
                        .addToBackStack(tag).remove(fragmentManager.findFragmentByTag("listFragment")).commit();
            }
        });

    }

    @Override
    public void showBreedList(List<String> breedList) {
        Log.d(TAG, "showBreedList: ");
        adapter.update(breedList);
    }

    @Override
    public void showBreedImages(List<String> breedList) {

    }

    @Override
    public void showBreedFavorites(List<Favorite> list) {

    }

}