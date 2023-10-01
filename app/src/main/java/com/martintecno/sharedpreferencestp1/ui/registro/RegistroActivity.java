package com.martintecno.sharedpreferencestp1.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.martintecno.sharedpreferencestp1.R;
import com.martintecno.sharedpreferencestp1.databinding.ActivityRegistroBinding;
import com.martintecno.sharedpreferencestp1.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel mv;

    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   boolean OPC = getIntent().getBooleanExtra("OPC", false);
        int ID = getIntent().getIntExtra("ID", 0);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        binding.BTNGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.ActualizarRegistrar(binding.ETDni.getText().toString(),
                                    binding.ETApellido.getText().toString(),
                                    binding.ETNombre.getText().toString(),
                                    binding.ETCorreo.getText().toString(),
                                    binding.ETContraseA.getText().toString());
            }
        });

        mv.getBotonM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.BTNGuardar.setText(s);
            }
        });

        mv.getTituloM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.TVCartel.setText(s);
            }
        });

        mv.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.ETDni.setText(usuario.getDni());
                binding.ETApellido.setText(usuario.getApellido());
                binding.ETNombre.setText(usuario.getNombre());
                binding.ETCorreo.setText(usuario.getCorreo());
                binding.ETContraseA.setText(usuario.getContraseña());
            }
        });

        /*-------------------*/
        mv.cargarSesion(ID);

    }
}