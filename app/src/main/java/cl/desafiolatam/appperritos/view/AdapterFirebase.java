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
    private List<Favorite> listFavorite;
    private String upperBreed;

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
        upperBreed = listFavorite.get(position).getBreed().substring(0, 1).toUpperCase() + listFavorite.get(position).getBreed().substring(1);
        holder.name.setText(upperBreed);
        holder.time.setText(listFavorite.get(position).getTimesStamp());
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
        TextView time;
        ImageView imageView;

        public FirebaseVH(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tvTimeStamp);
            name = itemView.findViewById(R.id.tvFirebase);
            imageView = itemView.findViewById(R.id.imageViewFirebase);
        }
    }
}
