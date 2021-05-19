package com.activity.androidshield;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

public class Permisos
{
    public Permisos(String[] lista_permisos)
    {
        this.lista_todos_permisos = lista_permisos;
        this.lista_permisos_peligrosos = new ArrayList<String>();

        this.permisos_peligrosos_binarios = new TreeMap<String, Integer>();
        InicializaMapaBinario();

        permisos_peligrosos = new TreeSet<String>();
        ListaPermisosPeligrosos();
    }

    public String[] GetTodosPermisos()
    {
        return lista_todos_permisos;
    }

    public ArrayList<String> GetPermisosPeligrosos()
    {
        return lista_permisos_peligrosos;
    }

    public ArrayList<Integer> GetPermisosPeligrososBinarios()
    {
        return new ArrayList<Integer>(permisos_peligrosos_binarios.values());
    }

    public void AniadirPermisosPeligrosos(String permiso)
    {
        // Añadimos el permiso peligroso a la lista y los ordenamos por orden alfabético
        lista_permisos_peligrosos.add(permiso);
        Collections.sort(this.lista_permisos_peligrosos);
        permisos_peligrosos_binarios.put(permiso, 1);
    }

    public static String[] ExtraerPermisosManifest(PackageManager pm, ApplicationInfo app)
    {
        String[] permissions = {};

        // Extraemos todos los permisos de la app
        try{
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

    public static TreeSet<String> GetListaPermisosPeligrosos()
    {
        return permisos_peligrosos;
    }

    private void InicializaMapaBinario()
    {
        permisos_peligrosos_binarios.put("android.permission.ACCEPT_HANDOVER", 0);
        permisos_peligrosos_binarios.put("android.permission.ACCESS_BACKGROUND_LOCATION", 0);
        permisos_peligrosos_binarios.put("android.permission.ACCESS_COARSE_LOCATION", 0);
        permisos_peligrosos_binarios.put("android.permission.ACCESS_FINE_LOCATION", 0);
        permisos_peligrosos_binarios.put("android.permission.ACCESS_MEDIA_LOCATION", 0);
        permisos_peligrosos_binarios.put("android.permission.ACTIVITY_RECOGNITION", 0);
        permisos_peligrosos_binarios.put("android.permission_binarios.put_VOICEMAIL", 0);
        permisos_peligrosos_binarios.put("android.permission.ANSWER_PHONE_CALLS", 0);
        permisos_peligrosos_binarios.put("android.permission.BODY_SENSORS", 0);
        permisos_peligrosos_binarios.put("android.permission.CALL_PHONE", 0);
        permisos_peligrosos_binarios.put("android.permission.CAMERA", 0);
        permisos_peligrosos_binarios.put("android.permission.GET_ACCOUNTS", 0);
        permisos_peligrosos_binarios.put("android.permission.PROCESS_OUTGOING_CALLS", 0);
        permisos_peligrosos_binarios.put("android.permission.READ_CALENDAR", 0);
        permisos_peligrosos_binarios.put("android.permission.READ_CALL_LOG", 0);
        permisos_peligrosos_binarios.put("android.permission.READ_CONTACTS", 0);
        permisos_peligrosos_binarios.put("android.permission.READ_EXTERNAL_STORAGE", 0);
        permisos_peligrosos_binarios.put("android.permission.READ_PHONE_NUMBERS", 0);
        permisos_peligrosos_binarios.put("android.permission.READ_PHONE_STATE", 0);
        permisos_peligrosos_binarios.put("android.permission.READ_SMS", 0);
        permisos_peligrosos_binarios.put("android.permission.RECEIVE_MMS", 0);
        permisos_peligrosos_binarios.put("android.permission.RECEIVE_SMS", 0);
        permisos_peligrosos_binarios.put("android.permission.RECEIVE_WAP_PUSH", 0);
        permisos_peligrosos_binarios.put("android.permission.RECORD_AUDIO", 0);
        permisos_peligrosos_binarios.put("android.permission.SEND_SMS", 0);
        permisos_peligrosos_binarios.put("android.permission.USE_SIP", 0);
        permisos_peligrosos_binarios.put("android.permission.WRITE_CALENDAR", 0);
        permisos_peligrosos_binarios.put("android.permission.WRITE_CALL_LOG", 0);
        permisos_peligrosos_binarios.put("android.permission.WRITE_CONTACTS", 0);
        permisos_peligrosos_binarios.put("android.permission.WRITE_EXTERNAL_STORAGE", 0);
    }

    private static void ListaPermisosPeligrosos()
    {
        permisos_peligrosos.add("android.permission.ACCEPT_HANDOVER");
        permisos_peligrosos.add("android.permission.ACCESS_BACKGROUND_LOCATION");
        permisos_peligrosos.add("android.permission.ACCESS_COARSE_LOCATION");
        permisos_peligrosos.add("android.permission.ACCESS_FINE_LOCATION");
        permisos_peligrosos.add("android.permission.ACCESS_MEDIA_LOCATION");
        permisos_peligrosos.add("android.permission.ACTIVITY_RECOGNITION");
        permisos_peligrosos.add("android.permission.ADD_VOICEMAIL");
        permisos_peligrosos.add("android.permission.ANSWER_PHONE_CALLS");
        permisos_peligrosos.add("android.permission.BODY_SENSORS");
        permisos_peligrosos.add("android.permission.CALL_PHONE");
        permisos_peligrosos.add("android.permission.CAMERA");
        permisos_peligrosos.add("android.permission.GET_ACCOUNTS");
        permisos_peligrosos.add("android.permission.PROCESS_OUTGOING_CALLS");
        permisos_peligrosos.add("android.permission.READ_CALENDAR");
        permisos_peligrosos.add("android.permission.READ_CALL_LOG");
        permisos_peligrosos.add("android.permission.READ_CONTACTS");
        permisos_peligrosos.add("android.permission.READ_EXTERNAL_STORAGE");
        permisos_peligrosos.add("android.permission.READ_PHONE_NUMBERS");
        permisos_peligrosos.add("android.permission.READ_PHONE_STATE");
        permisos_peligrosos.add("android.permission.READ_SMS");
        permisos_peligrosos.add("android.permission.RECEIVE_MMS");
        permisos_peligrosos.add("android.permission.RECEIVE_SMS");
        permisos_peligrosos.add("android.permission.RECEIVE_WAP_PUSH");
        permisos_peligrosos.add("android.permission.RECORD_AUDIO");
        permisos_peligrosos.add("android.permission.SEND_SMS");
        permisos_peligrosos.add("android.permission.USE_SIP");
        permisos_peligrosos.add("android.permission.WRITE_CALENDAR");
        permisos_peligrosos.add("android.permission.WRITE_CALL_LOG");
        permisos_peligrosos.add("android.permission.WRITE_CONTACTS");
        permisos_peligrosos.add("android.permission.WRITE_EXTERNAL_STORAGE");
    }

    private String[] lista_todos_permisos;
    private ArrayList<String> lista_permisos_peligrosos;
    private TreeMap<String, Integer> permisos_peligrosos_binarios;
    private static TreeSet<String> permisos_peligrosos;
}
