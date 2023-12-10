package com.example.mygo4lunch.fragments;

import static com.example.mygo4lunch.ui.RestaurantDetailsActivity.RESTAURANT_PLACE_ID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.mygo4lunch.R;
import com.example.mygo4lunch.databinding.FragmentMapsBinding;
import com.example.mygo4lunch.factory.Go4LunchFactory;
import com.example.mygo4lunch.injections.Injection;
import com.example.mygo4lunch.models.Restaurant;
import com.example.mygo4lunch.ui.RestaurantDetailsActivity;
import com.example.mygo4lunch.viewModel.MapsViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MapsFragment extends BaseFragment implements OnMapReadyCallback, EasyPermissions.PermissionCallbacks {

    private FragmentMapsBinding binding;

    private MapsViewModel viewModel;

    private GoogleMap googleMap;

    private MapView mapView;

    private Location userLocation;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        configureFragmentOnCreateView(view);
        configureMapView(savedInstanceState);
        return view;
    }

    private void configureMapView(Bundle savedInstanceState) {
        mapView = binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (googleMap != null) {
            String STATE_KEY_MAP_CAMERA = "keymap";
            outState.putParcelable(STATE_KEY_MAP_CAMERA, googleMap.getCameraPosition());
        }
    }

    @Override
        public void onMapReady(GoogleMap googleMap) {
            this.googleMap = googleMap;
            googleMap.setIndoorEnabled(false);
            updateLocationUI();
            googleMap.setOnCameraMoveListener(this::onMarkerClick);
        }

    private void updateLocationUI() {
        try {
            if (userLocation !=null) {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()), 16));
            }
        } catch (SecurityException e) {
            Log.e("Exception:", e.getMessage());
        }

        Log.d("location update", "update UI");

    }
    @SuppressLint("PotentialBehaviorOverride")
    private void onMarkerClick() {
        googleMap.setOnMarkerClickListener( marker -> {
            String placeId = (String) marker.getTag();
            Intent intent = new Intent(getActivity(), RestaurantDetailsActivity.class);
            intent.putExtra(RESTAURANT_PLACE_ID, placeId);
            startActivity(intent);
            return false;
        });
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }

  @Override
    public void getLocationUser(Location locationUser) {
        userLocation = locationUser;
        viewModel.fetchWorkMatesGoing();
        viewModel.workMatesIdMutableLiveData
                .observe(getViewLifecycleOwner(), workMatesIds ->
                        viewModel.getRestaurantList(locationUser.getLatitude(), locationUser.getLongitude())
                                .observe(getViewLifecycleOwner(), restaurants ->
                                        setMapMarkers(restaurants, workMatesIds)));

        if (googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory
                    .newLatLngZoom(new LatLng(locationUser.getLatitude(), locationUser.getLongitude()), 16));
        }

      Log.d("user location", "location user fetched");

    }

    

    private void setMapMarkers(List<Restaurant> restaurants, List<String> workMatesIds) {
        if (googleMap != null) {
            googleMap.clear();

            for (Restaurant restaurant : restaurants) {
                int iconResource = (workMatesIds.contains(restaurant.getRestaurantID())) ?
                        R.drawable.icon_location_selected : R.drawable.icon_location_normal;
                LatLng positionRestaurant = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
                googleMap.addMarker(new MarkerOptions()
                        .position(positionRestaurant)
                        .icon(BitmapDescriptorFactory.fromResource(iconResource))
                        .title(restaurant.getName()))
                        .setTag(restaurant.getRestaurantID());
                onMarkerClick();
            }
        }
    }

    @Override
    protected void configureFragmentOnCreateView(View view) {
        viewModel = obtainViewModel();
    }

    private MapsViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(requireActivity(), viewModelFactory).get(MapsViewModel.class);
    }

    public void displayRestaurant(LatLng latLng, String name, String id) {
        if (latLng != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            int iconResource = R.drawable.icon_location_normal;
            LatLng positionRestaurant = new LatLng(latLng.latitude, latLng.longitude);
            googleMap.addMarker(new MarkerOptions()
                            .position(positionRestaurant)
                            .icon(BitmapDescriptorFactory.fromResource(iconResource))
                            .title(name))
                    .setTag(id);

            onMarkerClick();
        }
    }
}

