package com.xer.covidqr

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception


open class MainActivity2 : AppCompatActivity() {

    var consult1 = "";
    var consult2 = "";
    var consult3 = "";
    var consult4 = "";
    var consult5 = "";
    var consult6 = "";
    var consult7 = "";



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        println("SE ABRIO LA SEGUNDA VENTANA")


        var consulta1 = findViewById<Switch>(R.id.switch1)
        var consulta2 = findViewById<Switch>(R.id.switch2)
        var consulta3 = findViewById<Switch>(R.id.switch3)
        var consulta4 = findViewById<Switch>(R.id.switch4)
        var consulta5 = findViewById<Switch>(R.id.switch5)
        var consulta6 = findViewById<Switch>(R.id.switch6)
        var consulta7 = findViewById<Switch>(R.id.switch7)
        var btnConsultas = findViewById<Button>(R.id.btnConsultas)

        btnConsultas.setOnClickListener(){

            if(consulta1.isChecked()){
                consult1 ="SI"
            }else{
                consult1 ="NO"
            }
            if(consulta2.isChecked()){
                consult2 ="SI"
            }else{
                consult2 ="NO"
            }
            if(consulta3.isChecked()){
                consult3 ="SI"
            }else{
                consult3 ="NO"
            }
            if(consulta4.isChecked()){
                consult4 ="SI"
            }else{
                consult4 ="NO"
            }
            if(consulta5.isChecked()){
                consult5 ="SI"
            }else{
                consult5 ="NO"
            }
            if(consulta6.isChecked()){
                consult6 ="SI"
            }else{
                consult6 ="NO"
            }
            if(consulta7.isChecked()){
                consult7 ="SI"
            }else{
                consult7 ="NO"
            }

            /*
            \n
             */

            var textoSecundario = "DECLARACION JURADA" +
                    "\n ¿ Ha desarrollado en los ultimos 5 dias episodios febrilles con temperaturas mayores a los 37,5° ? \n-----> ${consult1}\n" +
                    "\n ¿ En los ultimos 14 dias, he estado en contacto con personas sospechosas o confirmadas de COVID-19 ? \n-----> ${consult2}\n" +
                    "\n ¿ En los ultimos 14 dias, ha permanecido o vicitado alguno de los hospitales COVID-19 en el ambito de la Republica Argentina ? \n-----> ${consult3}\n" +
                    "\n ¿ Ha regresado de algun vieje del exterior en los ultimos 14 dias ? \n-----> ${consult4}\n" +
                    "\n ¿ Ha estado expuesto a grupos humanos numerosos desconocidos. sin respetar la distancia social establecida ? \n-----> ${consult5}\n" +
                    "\n ¿ Permanecio en lugares cerrados con grupos humanos numerosos por mas de 30 min seguidos, respetando o no la distancia social ? \n-----> ${consult6}\n" +
                    "\n En el dia de la fecha ¿ ha utilizado el transporte publico ? \n-----> ${consult7}\n"


            //Recibe textoPrincipal del MainActivity y lo guarda en datosPersonales
            var datosPesonales = getIntent().getStringExtra("variable_string");
            var textoFinal =" $datosPesonales \n $textoSecundario"
            println(textoFinal)

            val intento1 = Intent(this, MainActivity3::class.java)
            intento1.putExtra("variable_string1", textoFinal);
            startActivity(intento1)

        }


        var btnCorrejirFormulario = findViewById<Button>(R.id.btnCorrejirFormulario)

        btnCorrejirFormulario.setOnClickListener(){

            try {
                val stream: FileOutputStream = openFileOutput(
                        "Datos.txt", Context.MODE_PRIVATE)
                val fichero = PrintWriter(stream)
                fichero.close()
            }catch (e: Exception){

            }

            val fichero = BufferedReader(
                    InputStreamReader(openFileInput("Datos.txt")))
            val texto = fichero.use(BufferedReader::readText)
            println("Datos guardados en el archivo local Datos.txt ----> "+texto)



            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            println("EL FORMULARIO ESTA VACIO")

        }
    }
}