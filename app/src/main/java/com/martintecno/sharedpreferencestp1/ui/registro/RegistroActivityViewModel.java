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

        if(ID != -1){
            TituloM.setValue("Perfil de usuario");
            BotonM.setValue("Guardar");

            usuarioM.setValue(ApiClient.getUsuario(context, ID));
        }else{
            TituloM.setValue("Registrar Usuario");
            BotonM.setValue("Registrar");
        }
    }


    public void ActualizarRegistrar(int ID,String dni, String apellido, String nombre, String correo, String contraseña){

        Usuario usuario = new Usuario(dni,apellido,nombre,correo,contraseña);

        if(ID != -1){

            ApiClient.ActualizarUsuario(context, usuario,ID);

            Toast.makeText(context, "Datos actualizados con exito", Toast.LENGTH_SHORT).show();

            this.cargarSesion(ID);

        }else{



            int IDNueva = ApiClient.registrar(context, usuario);

            Toast.makeText(context, "Registrado con exito", Toast.LENGTH_SHORT).show();

            this.cargarSesion(IDNueva);

        }




    }
}
