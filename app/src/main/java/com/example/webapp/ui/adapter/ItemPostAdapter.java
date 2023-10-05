package com.example.webapp.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapp.R;
import com.example.webapp.databinding.ItemPostBinding;
import com.example.webapp.ui.viewModel.Post;

import java.util.ArrayList;
import java.util.List;

public class ItemPostAdapter extends RecyclerView.Adapter<ItemPostAdapter.ViewHolder> {

    private ArrayList<Post> dataModelList;
    private Context context;

    public ItemPostAdapter(ArrayList<Post> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    public void setData(List<Post> filterList) {
        this.dataModelList = new ArrayList<>(filterList);
        notifyDataSetChanged();
    }


    @Override
    public ItemPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        ItemPostBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_post, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemRowBinding.setPost(dataModelList.get(position));
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemPostBinding itemRowBinding;


        public ViewHolder(ItemPostBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

    }

}
