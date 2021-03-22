package com.activity.androidshield;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

public class Permisos
{
    public Permisos(String[] lista_permisos)
    {
        this.lista_permisos = lista_permisos;
    }

    public String[] GetPermisos(){
        return lista_permisos;
    }

    public static String[] GetPermisosApp(PackageManager pm, ApplicationInfo app)
    {
        String[] permissions = {};

        try
        {
            PackageInfo package_info = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);
            permissions = package_info.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            System.err.println(e);
        }

        return permissions;
    }

    private String[] lista_permisos;
}
