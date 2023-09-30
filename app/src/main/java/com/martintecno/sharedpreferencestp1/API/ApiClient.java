package com.martintecno.sharedpreferencestp1.API;

import android.content.Context;
import android.content.SharedPreferences;

import com.martintecno.sharedpreferencestp1.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ApiClient {

    private static SharedPreferences SP;

    private static SharedPreferences conectar(Context context){
        if(SP == null){
            SP = context.getSharedPreferences("datos",0);
        }
        return SP;
    }


    public static Usuario getUsuario(Context context,int ID){

        SharedPreferences sp = conectar(context);

        String dni = sp.getString("dni"+ID,"-1");
        String apellido = sp.getString("apellido"+ID,"-1");
        String nombre = sp.getString("nombre"+ID,"-1");
        String correo = sp.getString("correo"+ID,"-1");
        String contraseña = sp.getString("contraseña"+ID,"-1");

        Usuario usuario = new Usuario(dni,apellido,nombre,correo,contraseña);

        return usuario;
    }

    public static int registrar(Context context, Usuario usuario){

        SharedPreferences sp = conectar(context);

        SharedPreferences.Editor editor = sp.edit();

        int ID = 1;

        while (sp.contains("correo" + ID)) {
            ID++;
        }
        editor.putString("dni"+ID,usuario.getDni());
        editor.putString("apellido"+ID, usuario.getApellido());
        editor.putString("nombre"+ID, usuario.getNombre());
        editor.putString("correo"+ID, usuario.getCorreo());
        editor.putString("contraseña"+ID, usuario.getContraseña());

        editor.commit();
        return ID;
    }


    public static int ingresar(Context context,String correo_, String contraseña_) {


        int ID = 1;
        Usuario usuario = null;

        SharedPreferences sp = conectar(context);

        while (sp.contains("correo"+ID) && sp.contains("contraseña"+ID)) {

            String correo = sp.getString("correo" + ID, "-1");
            String contraseña = sp.getString("contraseña" + ID, "-1");

            if (correo_.equals(correo) && contraseña_.equals(contraseña)) {

                String dni = sp.getString("dni" + ID, "-1");
                String apellido = sp.getString("apellido" + ID, "-1");
                String nombre = sp.getString("nombre" + ID, "-1");

                usuario = new Usuario(dni, apellido, nombre, correo, contraseña);
                return ID;

            }
            ID++;
        }
        return -1;
    }



    public static int ActualizarUsuario(Context context, Usuario usuario, int ID) {

        SharedPreferences sp = conectar(context);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("dni" + ID, usuario.getDni());
        editor.putString("apellido" + ID, usuario.getApellido());
        editor.putString("nombre" + ID, usuario.getNombre());
        editor.putString("correo" + ID, usuario.getCorreo());
        editor.putString("contraseña" + ID, usuario.getContraseña());

        editor.commit();
        return ID;
    }

}
