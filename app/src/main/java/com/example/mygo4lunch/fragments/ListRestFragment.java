package com.example.mygo4lunch.fragments;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mygo4lunch.adapters.RestaurantAdapter;
import com.example.mygo4lunch.databinding.FragmentListRestBinding;
import com.example.mygo4lunch.factory.Go4LunchFactory;
import com.example.mygo4lunch.injections.Injection;
import com.example.mygo4lunch.models.Restaurant;
import com.example.mygo4lunch.viewModel.RestaurantViewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ListRestFragment extends BaseFragment {

    public static final String TAG = ListRestFragment.class.getSimpleName(); // ca sert a quoi le tag

    private RestaurantAdapter adapter;

    private RestaurantViewModel viewModel;

    private FragmentListRestBinding binding;



    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListRestBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        configureFragmentOnCreateView(view);
        this.configureFragmentOnCreateView(view);
        return view;
    }

    @Override
    public void getLocationUser(Location locationUser) {
        if (isAdded()) {
            fetchRestaurantList(locationUser);
        }
    }

    private void fetchRestaurantList(Location location) {
        viewModel.fetchWorkMatesGoing();
        viewModel.workMatesIdMutableLiveData
                .observe(getViewLifecycleOwner(), workMatesIds ->
                        viewModel.getRestaurants()
                                .observe(getViewLifecycleOwner(), list ->
                                        changeAndNotifyAdapterChange(list, workMatesIds)));

    }

    private void changeAndNotifyAdapterChange (List<Restaurant> restaurants, List<String> workMatesIds) {
        adapter.setRestaurantList(restaurants, workMatesIds);
    }

    @Override
    protected void configureFragmentOnCreateView(View view) {
        viewModel = obtainViewModel();
        initRecyclerView();

    }

    private RestaurantViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(requireActivity(), viewModelFactory).get(RestaurantViewModel.class);
    }

    private void initRecyclerView() {
        adapter = new RestaurantAdapter();
        binding.restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.restaurantRecyclerView.setAdapter(adapter);

    }
}