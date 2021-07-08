package com.xer.covidqr

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fichero = BufferedReader(
                InputStreamReader(openFileInput("Datos.txt")))
        val texto = fichero.use(BufferedReader::readText)
        println("Datos guardados en el archivo local Datos.txt ----> "+texto)


        if (!texto.isEmpty()){
            val intento1 = Intent(this, MainActivity2::class.java)
            intento1.putExtra("variable_string", texto);
            startActivity(intento1)
            println(texto)
        }else {
            val stream: FileOutputStream = openFileOutput(
                    "Datos.txt", Context.MODE_PRIVATE)
            val fichero = PrintWriter(stream)
            fichero.close()
        }

        var nombreApellido = findViewById<EditText>(R.id.etName)
        var DNI = findViewById<EditText>(R.id.etDNI)
        var direccion = findViewById<EditText>(R.id.etDireccion)
        var correoElectronico = findViewById<EditText>(R.id.etEmail)
        var celular = findViewById<EditText>(R.id.etCelular)
        var btnCargar = findViewById<Button>(R.id.btnCargar)

       btnCargar.setOnClickListener(){

           var etNombreApellido = nombreApellido.text.toString().trim()
           var etDNI = DNI.text.toString().trim()
           var etDireccion = direccion.text.toString().trim()
           var etCorreoElectronico = correoElectronico.text.toString().trim()
           var etCelular = celular.text.toString().trim()

           if(etNombreApellido.isEmpty()){
               Toast.makeText(this, "Nombre y apellido incompleto.", Toast.LENGTH_SHORT).show();
           }
           else if(etDNI.isEmpty()){
           Toast.makeText(this, "DNI incompleto.", Toast.LENGTH_SHORT).show();
           }
           else if(etDireccion.isEmpty()){
               Toast.makeText(this, "Direccion incompleta.", Toast.LENGTH_SHORT).show();
           }
           else if(etCorreoElectronico.isEmpty()){
               Toast.makeText(this, "Correo electronico incompleto.", Toast.LENGTH_SHORT).show();
           }
           else if(etCelular.isEmpty()){
               Toast.makeText(this, "Celular incompleto.", Toast.LENGTH_SHORT).show();
           }else{
               val Formulario: Book = Book("$etNombreApellido", "$etDNI", "$etDireccion", "$etCorreoElectronico", "$etCelular")
               val textoPrincipal ="DATOS PERSONALES \n Nombre completo : ${Formulario.nombApellid} \n DNI : ${Formulario.DNI} \n Direccion : ${Formulario.direccion} \n Correo : ${Formulario.correoElectronico} \n Celular : ${Formulario.celular} \n"
               println("Salida : $textoPrincipal")

               //Se conecta mediante el Intent a MainActivity2
               val intento1 = Intent(this, MainActivity2::class.java)

               //Se envia la variable textoPrincipal a MainActivity2
               intento1.putExtra("variable_string", textoPrincipal);
               startActivity(intento1)

               val intento3 = Intent(this, MainActivity3::class.java)
               intento1.putExtra("variable_string2", etNombreApellido);


               try {
                   val stream: FileOutputStream = openFileOutput(
                           "Datos.txt", Context.MODE_PRIVATE)
                   val fichero = PrintWriter(stream)
                   fichero.println(textoPrincipal)
                   fichero.close()
               }catch (e: Exception){

               }

               val fichero = BufferedReader(
                       InputStreamReader(openFileInput("Datos.txt")))
               val texto = fichero.use(BufferedReader::readText)
               println("Se han guardados los datos del formulario dentro del archivo local Datos.txt ----> "+texto)

           }
       }
    }
}