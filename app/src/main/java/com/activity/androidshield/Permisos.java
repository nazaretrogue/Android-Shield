package com.activity.androidshield;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.TreeSet;

public class Permisos
{
    public Permisos(String[] lista_permisos)
    {
        this.lista_permisos = lista_permisos;

        permisos_peligrosos = new TreeSet<String>();
        ListaPermisosPeligrosos();
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

    public static boolean EsPermisoPeligroso(String permiso)
    {
        return permisos_peligrosos.contains(permiso);
    }

    private static void ListaPermisosPeligrosos()
    {
        permisos_peligrosos.add("android.permission.READ_CALENDAR");
        permisos_peligrosos.add("android.permission.WRITE_CALENDAR");
        permisos_peligrosos.add("android.permission.CAMERA");
        permisos_peligrosos.add("android.permission.READ_CONTACTS");
        permisos_peligrosos.add("android.permission.WRITE_CONTACTS");
        permisos_peligrosos.add("android.permission.GET_ACCOUNTS");
        permisos_peligrosos.add("android.permission.ACCESS_FINE_LOCATION");
        permisos_peligrosos.add("android.permission.ACCESS_COARSE_LOCATION");
        permisos_peligrosos.add("android.permission.RECORD_AUDIO");
        permisos_peligrosos.add("android.permission.READ_PHONE_STATE");
        permisos_peligrosos.add("android.permission.READ_PHONE_NUMBERS");
        permisos_peligrosos.add("android.permission.CALL_PHONE");
        permisos_peligrosos.add("android.permission.ANSWER_PHONE_CALLS");
        permisos_peligrosos.add("android.permission.READ_CALL_LOG");
        permisos_peligrosos.add("android.permission.WRITE_CALL_LOG");
        permisos_peligrosos.add("android.permission.ADD_VOICEMAIL");
        permisos_peligrosos.add("android.permission.USE_SIP");
        permisos_peligrosos.add("android.permission.PROCESS_OUTGOING_CALLS");
        permisos_peligrosos.add("android.permission.BODY_SENSORS");
        permisos_peligrosos.add("android.permission.SEND_SMS");
        permisos_peligrosos.add("android.permission.RECEIVE_SMS");
        permisos_peligrosos.add("android.permission.READ_SMS");
        permisos_peligrosos.add("android.permission.RECEIVE_WAP_PUSH");
        permisos_peligrosos.add("android.permission.RECEIVE_MMS");
        permisos_peligrosos.add("android.permission.READ_EXTERNAL_STORAGE");
        permisos_peligrosos.add("android.permission.WRITE_EXTERNAL_STORAGE");
        permisos_peligrosos.add("android.permission.ACCESS_MEDIA_LOCATION");
        permisos_peligrosos.add("android.permission.ACCEPT_HANDOVER");
        permisos_peligrosos.add("android.permission.ACCESS_BACKGROUND_LOCATION");
        permisos_peligrosos.add("android.permission.ACTIVITY_RECOGNITION");
    }



    private String[] lista_permisos;
    private static TreeSet<String> permisos_peligrosos;
}
