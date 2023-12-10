package com.example.mygo4lunch.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mygo4lunch.databinding.ActivitySettingsBinding;
import com.example.mygo4lunch.factory.Go4LunchFactory;
import com.example.mygo4lunch.injections.Injection;
import com.example.mygo4lunch.viewModel.SettingsViewModel;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private SettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        settingsViewModel = obtainViewModel();

        handleSettings();

    }

    private void initView() {
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private SettingsViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(this, viewModelFactory).get(SettingsViewModel.class);
    }

    private void handleSettings() {
        settingsViewModel.configureSaveDataRepo(getApplicationContext());
        boolean notificationStatus = settingsViewModel.getStatusNotification(getApplicationContext());
        binding.notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            settingsViewModel.notificationStateChanged(isChecked, getApplicationContext());
                });
        binding.notificationSwitch.setChecked(notificationStatus);
    }
}