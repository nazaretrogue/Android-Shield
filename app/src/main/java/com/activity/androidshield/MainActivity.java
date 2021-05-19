package com.activity.androidshield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicio de python para la ejecución del modelo entrenado
        if (!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        // Elemento de la UI para mostrar el análisis en forma de texto
        texto_analisis = (TextView)findViewById(R.id.texto_analisis);
        texto_analisis.setMovementMethod(new ScrollingMovementMethod());

        // Botón para inicial el análisis
        FloatingActionButton boton_analisis = findViewById(R.id.boton_analisis);
        boton_analisis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Se va a iniciar el análisis", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Procesa();
                texto_analisis.setText(analisis_apps);
            }
        });
    }

    public void Procesa()
    {
        // Extraemos el contexto en el que se ejecuta la app. Después, extraemos la lista de
        // todas las aplicaciones instaladas
        Context context = this;
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> info = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        String[] lista_permisos;

        for(ApplicationInfo app_info: info)
        {
            // Extraemos los permisos de cada app de los metadatos de la apk
            lista_permisos = Permisos.ExtraerPermisosManifest(pm, app_info);
            Permisos permisos_app = new Permisos(lista_permisos);

            // Creamos un objeto aplicación para que sea más sencillo acceder después a los
            // permisos peligrosos
            Aplicacion app = new Aplicacion(app_info, permisos_app);

            if(lista_permisos != null)
            {
                for(int i=0; i<lista_permisos.length; i++)
                    if (Permisos.EsPermisoPeligroso(lista_permisos[i]))
                        app.SetPermisoPeligroso(lista_permisos[i]);
            }

            // Predecimos el tipo de app con los datos extraídos
            PrediceApp(app);
        }
    }

    protected void PrediceApp(Aplicacion app){
        // Creamos una instancia de Python para cargar el modelo entrenado
        Python py = Python.getInstance();
        PyObject modelo = py.getModule("script_prediccion");

        // Predecimos la app a través del modelo de Python
        List<PyObject> obj = modelo.callAttr("prediccion_modelo", app.GetNombre(), app.GetPermisos().GetPermisosPeligrososBinarios().toArray()).asList();
        String mensaje = "";

        for(int i=0; i<obj.size(); i++)
            mensaje += obj.get(i).toJava(String.class);

        analisis_apps += mensaje+"\n";
    }

    private TextView texto_analisis;
    private String analisis_apps = "";
}