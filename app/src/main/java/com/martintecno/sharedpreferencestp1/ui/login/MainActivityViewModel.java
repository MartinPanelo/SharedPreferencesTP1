package com.martintecno.sharedpreferencestp1.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.martintecno.sharedpreferencestp1.API.ApiClient;
import com.martintecno.sharedpreferencestp1.model.Usuario;
import com.martintecno.sharedpreferencestp1.ui.registro.RegistroActivity;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }


    public void RegistrarUsuario(){

        Intent intent = new Intent(context, RegistroActivity.class);
       // intent.putExtra("OPC", false); // para avisar que es registro
     //   intent.putExtra("ID", -1); no hace falta porque es -1 por defecto
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }


    public void ingresar(String correo_, String contraseña_){

        if(ApiClient.ingresar(context, correo_, contraseña_) != -1){




            Intent intent = new Intent(context, RegistroActivity.class);

         //   intent.putExtra("OPC", true); // para avisar que es ingreso
            intent.putExtra("ID", ApiClient.ingresar(context, correo_, contraseña_));

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            Toast.makeText(context, "Ingreso con exito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }



    }
















}
