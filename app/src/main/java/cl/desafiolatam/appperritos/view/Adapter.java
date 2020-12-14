package cl.desafiolatam.appperritos.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cl.desafiolatam.appperritos.R;
import cl.desafiolatam.appperritos.model.Breed;

public class Adapter extends RecyclerView.Adapter<Adapter.BreedVH> {

    private static final String TAG = "Adapter";
    private List<String> breedList;
    private Context mContext;
    private OnItemClickListener listener;

    public Adapter(Context mContext, List<String> breedList) {
        this.mContext = mContext;
        this.breedList = breedList;

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Adapter.BreedVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dog, parent, false);
        return new BreedVH(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull BreedVH holder, int position) {
        //Log.d(TAG, "onBindViewHolder: ");
        holder.bind(this.breedList.get(position));
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + breedList.size());
        return breedList.size();
    }

    public void update(List<String> breedList) {
        this.breedList = breedList;
        notifyDataSetChanged();
    }

    public class BreedVH extends RecyclerView.ViewHolder {

        Breed breedList;
        TextView textView;
        ImageView imageView;

        public BreedVH(@NonNull View itemView, final Adapter.OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewBreed);
            itemView.setOnClickListener(v -> {
                Log.d(TAG, "BreedVH: ONCLICK");
                int position = getAdapterPosition();
                listener.onItemClick(position);
            });
        }

        public void bind(String breed) {

            textView.setText(breed.toUpperCase());

        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {

        }
    }

}
