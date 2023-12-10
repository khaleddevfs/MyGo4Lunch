package com.example.mygo4lunch.adapters;

import android.content.Context;
import android.graphics.Typeface;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygo4lunch.R;
import com.example.mygo4lunch.databinding.WorkmatesItemLayoutBinding;
import com.example.mygo4lunch.models.WorkMate;

public class WorkMateViewHolder extends RecyclerView.ViewHolder {

    public WorkmatesItemLayoutBinding binding;
    public WorkMateViewHolder(WorkmatesItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void updateWorkMates(WorkMate workMate) {
        Context context = binding.getRoot().getContext();
        String workMateTextName= "";

        if (workMate.getWorkMateRestaurantChoice() != null) {
            workMateTextName = workMate.getName() + " " + "is eating" + " (" + workMate.getWorkMateRestaurantChoice().getRestaurantName() + ")";
            binding.nameTextView.setTypeface(binding.nameTextView.getTypeface(), Typeface.BOLD);
            binding.nameTextView.setTextColor(context.getResources().getColor(R.color.colorBlack));
        } else {
            binding.nameTextView.setTypeface(binding.nameTextView.getTypeface(), Typeface.ITALIC);
            binding.nameTextView.setTextColor(context.getResources().getColor(R.color.colorGrey));
            workMateTextName = workMate.getName() + " " + "has not decided yet";
        }

        binding.nameTextView.setText(workMateTextName);
        Glide.with(binding.recyclerViewWorkMateItemImageView.getContext())
                .load(workMate.getPhotoUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(binding.recyclerViewWorkMateItemImageView);
    }
}
