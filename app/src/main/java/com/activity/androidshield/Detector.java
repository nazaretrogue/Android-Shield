package com.activity.androidshield;

/*import scala.Tuple2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.util.MLUtils;*/

import android.content.Context;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.TreeSet;

public class Detector {

    public Detector(){}

    protected static void PreProcesa(Aplicacion app, Context context)
    {
        String nombre_app = app.GetNombre();
        String permisos_app = new String();

        ArrayList<String> lista_permisos = app.GetPermisos().GetPermisosPeligrosos();
        TreeSet<String> perms = Permisos.GetListaPermisosPeligrosos();

        for(int i=0; i<perms.size(); i++)
        {
            String cadena = perms.first();
            boolean contiene_permiso = lista_permisos.contains(cadena);

            if(contiene_permiso)
                permisos_app += "1";

            else
                permisos_app += "0";

            if(i+1 != perms.size())
                permisos_app += ",";

            else
                permisos_app += "1";

            perms.remove(cadena);
        }

        try {
            OutputStreamWriter os = new OutputStreamWriter(context.openFileOutput("data.txt", Context.MODE_APPEND));
            os.write(permisos_app);
            os.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
