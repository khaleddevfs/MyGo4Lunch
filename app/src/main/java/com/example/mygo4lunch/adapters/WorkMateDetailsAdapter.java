package com.example.mygo4lunch.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygo4lunch.databinding.WorkmatesItemLayoutBinding;
import com.example.mygo4lunch.models.WorkMate;

import java.util.List;

public class WorkMateDetailsAdapter extends RecyclerView.Adapter<WorkMateDetailsViewHolder> {

    private List<WorkMate> workMateList;


    @NonNull
    @Override
    public WorkMateDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WorkmatesItemLayoutBinding binding = WorkmatesItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new WorkMateDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkMateDetailsViewHolder holder, int position) {
        holder.updateWorkMates(workMateList.get(position));
    }

    @Override
    public int getItemCount() {
        if (workMateList !=null){
            return workMateList.size();
        }
        return 0;
    }

    public void setWorkMateList(List<WorkMate> workMateList) {
        this.workMateList = workMateList;
        notifyDataSetChanged();
    }
}
