package com.activity.androidshield;

import android.content.pm.ApplicationInfo;

public class Aplicacion
{
    public Aplicacion(ApplicationInfo info, Permisos permisos)
    {
        nombre = GetNombreApp(info.packageName);
        this.permisos = permisos;
    }

    public String GetNombre(){
        return nombre;
    }

    public void SetNombre(String nombre){
        this.nombre = nombre;
    }

    public Permisos GetPermisos(){
        return permisos;
    }

    public void SetPermisos(){
        this.permisos = permisos;
    }

    private String GetNombreApp(String app){
        String[] parts = app.split("\\.");
        return parts[parts.length-1];
    }

    private String nombre;
    private Permisos permisos;
}
