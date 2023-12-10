package com.example.mygo4lunch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mygo4lunch.adapters.WorkMateAdapter;
import com.example.mygo4lunch.databinding.FragmentWorkmatesBinding;
import com.example.mygo4lunch.factory.Go4LunchFactory;
import com.example.mygo4lunch.injections.Injection;
import com.example.mygo4lunch.models.WorkMate;
import com.example.mygo4lunch.viewModel.WorkMateViewModel;

import java.util.ArrayList;
import java.util.List;


public class WorkmatesFragment extends Fragment {

    private FragmentWorkmatesBinding binding;

    private List<WorkMate> workMate;

    private WorkMateAdapter workMateAdapter;

    private WorkMateViewModel workMateViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWorkmatesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        configureFragmentOnCreateView();
        return view;
    }

    protected void configureFragmentOnCreateView() {
        workMateViewModel = obtainViewModel();
        initRecyclerView();
        setupWorkMateList();

    }

    private WorkMateViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(requireActivity(), viewModelFactory).get(WorkMateViewModel.class);
    }

    private void initRecyclerView() {
        workMate = new ArrayList<>();
        workMateAdapter = new WorkMateAdapter(workMate);
        binding.restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.restaurantRecyclerView.setAdapter(workMateAdapter);
    }



    private void setupWorkMateList() {
        workMateViewModel.fetchListUsersFromFirebase();
        workMateViewModel.getWorkMates().observe(getViewLifecycleOwner(), this::showUsers);
    }

    private void showUsers(List<WorkMate> workMates) {
        this.workMate = workMates;
        workMateAdapter.updateWorkMates(this.workMate);
    }
}