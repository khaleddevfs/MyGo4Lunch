package com.example.mygo4lunch.ui;

import static com.example.mygo4lunch.viewModel.LoginViewModel.RC_SIGN_IN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mygo4lunch.databinding.ActivityLoginBinding;
import com.example.mygo4lunch.factory.Go4LunchFactory;
import com.example.mygo4lunch.injections.Injection;
import com.example.mygo4lunch.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    private LoginViewModel viewModel;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initView();

        initListener();

        viewModel = obtainViewModel();

        checkSessionUser();
        
    }




    private void initView() {
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);
    }

    private void initListener() {
        loginBinding.emailLoginButton.setOnClickListener(v -> viewModel.startLoginActivityEmail(LoginActivity.this));
        loginBinding.gmailLoginButton.setOnClickListener(v -> viewModel.startLoginActivityGoogle(LoginActivity.this));
        loginBinding.twitterLoginButton.setOnClickListener(v -> viewModel.startLoginActivityTwitter(LoginActivity.this));
    }


    private LoginViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
    }


    private void checkSessionUser() {
        viewModel.updateCurrentUser();
        if (viewModel.isCurrentUserLogged()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                viewModel.updateCurrentUser();
                Intent loginIntent = new Intent(this, MainActivity.class);
                startActivity(loginIntent);
            } else {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();    // il faut mettre quoi ici comme texte
            }
        }
    }
}