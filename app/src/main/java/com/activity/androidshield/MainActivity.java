package com.activity.androidshield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto_analisis = (TextView)findViewById(R.id.texto_analisis);
        texto_analisis.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton boton_analisis = findViewById(R.id.boton_analisis);

        boton_analisis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // Burbuja de texto en la parte inferior de la pantalla para informar de *mensaje*
                // Snackbar.make(view, "mensaje", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Procesa();
            }
        });
    }

    public void Procesa()
    {
        Context context = this;
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> info = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        String[] lista_permisos;
        String cad = new String();

        for(ApplicationInfo app_info: info)
        {
            lista_permisos = Permisos.GetPermisosApp(pm, app_info);
            Permisos permisos_app = new Permisos(lista_permisos);

            Aplicacion app = new Aplicacion(app_info, permisos_app);

            if(lista_permisos != null)
            {
                for (int i = 0; i < lista_permisos.length; i++) {
                    if (lista_permisos[i].equals("android.permission.CAMERA")) {
                        cad += "La app " + app.GetNombre() + " tiene permiso de camarica\n";
                    }
                }
            }
        }

        cad = cad + cad + cad + cad+ cad+cad+cad+cad+cad+cad+cad+cad+cad;

        texto_analisis.setText(cad);
    }

    private TextView texto_analisis;
}