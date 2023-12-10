package com.example.mygo4lunch.adapters;

import android.content.Context;
import android.graphics.Typeface;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygo4lunch.R;
import com.example.mygo4lunch.models.WorkMate;

public class WorkMateDetailsViewHolder extends RecyclerView.ViewHolder {

    public com.example.mygo4lunch.databinding.WorkmatesItemLayoutBinding binding;

    public WorkMateDetailsViewHolder(com.example.mygo4lunch.databinding.WorkmatesItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding =binding;
    }

    public void updateWorkMates(WorkMate workMate) {
        Context context = binding.getRoot().getContext();
        String workMateText = workMate.getName() + " " + context.getString(R.string.is_joining);
        binding.nameTextView.setTypeface(binding.nameTextView.getTypeface(), Typeface.BOLD);
        binding.nameTextView.setTextColor(context.getResources().getColor(R.color.colorBlack));
        binding.nameTextView.setText(workMateText);

        Glide.with(binding.recyclerViewWorkMateItemImageView.getContext())
                .load(workMate.getPhotoUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(binding.recyclerViewWorkMateItemImageView);
    }
}
