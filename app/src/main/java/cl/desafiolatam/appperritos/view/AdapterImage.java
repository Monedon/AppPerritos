package cl.desafiolatam.appperritos.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.desafiolatam.appperritos.R;

public class AdapterImage extends RecyclerView.Adapter<AdapterImage.ImageVH> {

    private static final String TAG = "Adapter";
    private List<String> breedListImage;
    private Context mContext;
    private OnItemLongClickListener listener;

    public AdapterImage(Context mContext, List<String> breedListImage) {
        this.mContext = mContext;
        this.breedListImage = breedListImage;

    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public AdapterImage.ImageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_image, parent, false);
        return new ImageVH(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterImage.ImageVH holder, int position) {
        Glide.with(mContext).load(breedListImage.get(position)).override(1000,800).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return breedListImage.size();
    }

    public void update(List<String> breedListImage) {
        this.breedListImage = breedListImage;
        notifyDataSetChanged();
    }

    public class ImageVH extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ImageVH(@NonNull View itemView, final AdapterImage.OnItemLongClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewBreed);
            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                listener.onItemLongClick(position);
                return true;
            });
        }

        public void setOnItemLongClickListener(AdapterView.OnItemClickListener listener) {

        }
    }

}
