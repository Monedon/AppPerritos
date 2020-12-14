package cl.desafiolatam.appperritos.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import cl.desafiolatam.appperritos.R;
import cl.desafiolatam.appperritos.model.Favorite;

public class AdapterFirebase extends RecyclerView.Adapter<AdapterFirebase.FirebaseVH> {

    private static final String TAG = "Adapter";
    private List<String> firebaseList;
    private Context mContext;
    List<Favorite> listFavorite;

    public AdapterFirebase(Context context, List<Favorite> getData) {
        this.mContext = context;
        this.listFavorite = getData;

    }

    @NonNull
    @Override
    public AdapterFirebase.FirebaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_firebase, parent, false);
        return new FirebaseVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFirebase.FirebaseVH holder, int position) {
        Log.d(TAG, "onBindViewHolder: lista" + listFavorite);
        holder.name.setText(listFavorite.get(position).getBreed());
        Glide.with(mContext).load(listFavorite.get(position).getImageURI()).override(300, 200).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public void update(List<Favorite> listFavorite) {
        this.listFavorite = listFavorite;
        notifyDataSetChanged();
    }


    public class FirebaseVH extends RecyclerView.ViewHolder{

        TextView name;
        ImageView imageView;

        public FirebaseVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvFirebase);
            imageView = itemView.findViewById(R.id.imageViewFirebase);
        }
    }
}
