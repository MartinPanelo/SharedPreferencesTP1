package com.martintecno.sharedpreferencestp1.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.martintecno.sharedpreferencestp1.API.ApiClient;
import com.martintecno.sharedpreferencestp1.R;
import com.martintecno.sharedpreferencestp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mv;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.BTNRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.RegistrarUsuario();
            }
        });

        binding.BTNIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.ingresar(binding.ETUsuario.getText().toString(),binding.ETContraseA.getText().toString());
            }
        });

    }
}