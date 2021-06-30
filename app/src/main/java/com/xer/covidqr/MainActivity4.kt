package com.xer.covidqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.xer.covidqr.databinding.ActivityMain3Binding


public class MainActivity4 : AppCompatActivity() {

    public lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    public fun initScaner() {
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
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}