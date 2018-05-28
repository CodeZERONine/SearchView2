package org.akshanshgusain.searchview2;

import android.app.LauncherActivity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holder>{
    private Context mContext;
    private List<String> countries;
    public Adapter(Context mContext,List<String> countries) {
        this.mContext=mContext;
        this.countries=countries;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        holder vh=new holder(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        String country=countries.get(position);
        holder.textview.setText(country);

    }

    public class holder extends RecyclerView.ViewHolder{
        public TextView textview;

        public holder(View itemView) {
            super(itemView);
            textview=(TextView)itemView.findViewById(R.id.textView1);
        }
    }

    public void setCountires(List<String> countries){
        this.countries=countries;
        notifyDataSetChanged();

    }

}
