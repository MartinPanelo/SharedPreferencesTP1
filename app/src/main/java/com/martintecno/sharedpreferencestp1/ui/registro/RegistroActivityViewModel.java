package com.martintecno.sharedpreferencestp1.ui.registro;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.martintecno.sharedpreferencestp1.API.ApiClient;
import com.martintecno.sharedpreferencestp1.model.Usuario;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<String> TituloM;

    private MutableLiveData<String> BotonM;
    private MutableLiveData<Usuario> usuarioM;

    private static int ID;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public LiveData<String> getTituloM(){
        if(TituloM == null){
            TituloM = new MutableLiveData<>();
        }
        return TituloM;
    }
    public LiveData<String> getBotonM(){
        if(BotonM == null){
            BotonM = new MutableLiveData<>();
        }
        return BotonM;
    }

    public LiveData<Usuario> getUsuarioM(){
        if(usuarioM == null){
            usuarioM = new MutableLiveData<>();
        }
        return usuarioM;
    }


    public void cargarSesion(int ID){

        if(ID != 0){
            TituloM.setValue("Perfil de usuario");
            BotonM.setValue("Guardar");
            this.ID = ID;

            usuarioM.setValue(ApiClient.getUsuario(context, ID));

        }else{
            TituloM.setValue("Registrar Usuario");
            BotonM.setValue("Registrar");
        }
    }


    public void ActualizarRegistrar(String dni, String apellido, String nombre, String correo, String contraseña){



        Usuario usuario = new Usuario(dni,apellido,nombre,correo,contraseña);

        if(this.ID != 0){

            this.ID = ApiClient.ActualizarUsuario(context, usuario,this.ID);

            if(this.ID != 0) {
                Toast.makeText(context, "Datos actualizados con exito", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Ya existe una cuenta con ese correo", Toast.LENGTH_SHORT).show();

            }
            this.cargarSesion(this.ID);

        }else{



            this.ID = ApiClient.registrar(context, usuario);

            if(this.ID != 0){
                Toast.makeText(context, "Registrado con exito", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(context, "Correo ya registrado", Toast.LENGTH_SHORT).show();

            }

            this.cargarSesion(this.ID);

        }




    }
}
