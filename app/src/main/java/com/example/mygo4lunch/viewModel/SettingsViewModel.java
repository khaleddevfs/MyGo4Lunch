package com.example.mygo4lunch.viewModel;

import android.content.Context;

import com.example.mygo4lunch.models.WorkMate;
import com.example.mygo4lunch.repositories.SaveDataRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;

public class SettingsViewModel extends BaseViewModel {

    private final SaveDataRepository saveDataRepository;

    private final WorkMate workMate;


    public SettingsViewModel(SaveDataRepository saveDataRepository, WorkMatesRepository workmatesRepository) {
        this.saveDataRepository = saveDataRepository;
        this.workmatesRepository = workmatesRepository;
        workMate = workmatesRepository.getActualUser();
    }


    public void configureSaveDataRepo(Context context) {
        if (saveDataRepository.getPreferences() == null) {
            saveDataRepository.configureContext(context);
        }
    }

    public void notificationStateChanged(boolean enabled, Context context) {
        saveDataRepository.configureContext(context);
        saveDataRepository.saveNotificationSettings(enabled, workMate.getUid());
    }

    public boolean getStatusNotification(Context context) {
        saveDataRepository.configureContext(context);
        return saveDataRepository.getNotificationSettings(workMate.getUid());
    }


}
