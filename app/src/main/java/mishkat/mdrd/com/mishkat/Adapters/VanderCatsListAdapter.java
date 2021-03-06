package mishkat.mdrd.com.mishkat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mishkat.mdrd.com.mishkat.DeskBoardsPages.Item_list_Activity;
import mishkat.mdrd.com.mishkat.DeskBoardsPages.Models.VendorDetailModel;
import mishkat.mdrd.com.mishkat.R;
import mishkat.mdrd.com.mishkat.SearchVanders.Models.GetAllVenderModel;

/**
 * Created by rahuljanagouda on 04/11/17.
 */

public class VanderCatsListAdapter extends RecyclerView.Adapter<VanderCatsListAdapter.HorizontalViewHolder> {

    private List<VendorDetailModel.CategoriesEntity> list;
    Context mcontext;
    public List<VendorDetailModel.CategoriesEntity> ListFiltered;

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cats_list_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    public VanderCatsListAdapter(Context context, List<VendorDetailModel.CategoriesEntity> mlist) {
        this.list = mlist;
        this.mcontext = context;
        this.ListFiltered = mlist;
    }


    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        /* holder.cardImage.setImageResource(R.drawable.phungdemac);*/
        holder.cardTitle.setText(ListFiltered.get(position).getName()+"");
       // Glide.with(mcontext).load(ListFiltered.get(position).getLogo()).placeholder(R.drawable.face2).error(R.drawable.face2).into(holder.Vender_Image);

    }

    @Override
    public int getItemCount() {
        return ListFiltered.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        ImageView Vender_Image;
        TextView cardTitle;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
           // Vender_Image = itemView.findViewById(R.id.Vender_Image);
            cardTitle = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Item_list_Activity.class);
                    Item_list_Activity.Vanderid = Integer.valueOf(ListFiltered.get(pos).getCat_id());
                    context.startActivity(intent);

                }
            });
        }
    }
}
