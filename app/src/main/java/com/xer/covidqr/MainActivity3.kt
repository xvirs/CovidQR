 package com.xer.covidqr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.xer.covidqr.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        var textFinal = findViewById<TextView>(R.id.txtTextoFinal)
        var emailEmpresa = findViewById<EditText>(R.id.etEmailEmpresa)
        var btnEnviar = findViewById<Button>(R.id.btnEnviarTextoFinal)

        var btnQR = findViewById<Button>(R.id.btnQR)


        var textoFinal = getIntent().getStringExtra("variable_string1");
        var Nombre = getIntent().getStringExtra("variable_string2");

        textFinal.setText(textoFinal)

        btnEnviar.setOnClickListener() {


            var emailEmpresa = emailEmpresa.text.toString().trim()


            val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(""))
            intentEmail.type = "plain/text"
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Declaracion Jurada")
            intentEmail.putExtra(Intent.EXTRA_TEXT, "${textoFinal}")
            intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailEmpresa))
            startActivity(Intent.createChooser(intentEmail, "Elige cliente de correo"))


        }



       btnQR.setOnClickListener(){
       
           initScaner()

       }

    }


    private fun initScaner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)

        if(result != null){
            if(result.contents==null){
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this,"${result.contents}", Toast.LENGTH_SHORT).show()

                var textoFinal = getIntent().getStringExtra("variable_string1");
                var emailEmpresa = result.contents


                val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(""))
                intentEmail.type = "plain/text"
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Declaracion Jurada")
                intentEmail.putExtra(Intent.EXTRA_TEXT, "${textoFinal}")
                intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailEmpresa))
                startActivity(Intent.createChooser(intentEmail, "Elige cliente de correo"))
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}