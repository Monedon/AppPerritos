package cl.desafiolatam.appperritos.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cl.desafiolatam.appperritos.R;
import cl.desafiolatam.appperritos.model.Favorite;
import cl.desafiolatam.appperritos.presenter.IPresenterView;
import cl.desafiolatam.appperritos.presenter.Presenter;

public class FireBaseFragment extends Fragment implements IPresenterView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "Adapter";
    private RecyclerView recyclerView;
    private AdapterFirebase adapter;
    private Presenter presenter = new Presenter(this);

    public FireBaseFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FireBaseFragment newInstance(String param1, String param2) {
        FireBaseFragment fragment = new FireBaseFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fire_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.recyclerViewFavs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        final List<Favorite> favList = presenter.readData();
        Log.d(TAG, "onViewCreated: fragmentfirebase" + favList);
        adapter = new AdapterFirebase(getContext(), favList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void showBreedList(List<String> list) {

    }

    @Override
    public void showBreedImages(List<String> breedList) {

    }

    @Override
    public void showBreedFavorites(List<Favorite> list) {
        adapter.update(list);
    }
}