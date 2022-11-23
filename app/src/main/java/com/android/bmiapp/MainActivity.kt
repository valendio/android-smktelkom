package com.android.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edt_nama : EditText
    private lateinit var edt_tinggi : EditText
    private lateinit var edt_bb : EditText
    private lateinit var edt_jk : Spinner
    private lateinit var result : TextView
    private lateinit var btn_submit : Button
    private lateinit var btn_clear: Button
    private lateinit var btn_exit: Button

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_nama = findViewById(R.id.nama)
        edt_tinggi = findViewById(R.id.tinggi)
        edt_bb = findViewById(R.id.berat)
        edt_jk = findViewById(R.id.jenis_kelamin)
        btn_submit = findViewById(R.id.btn_submit)
        btn_clear = findViewById(R.id.btn_clear)
        btn_exit = findViewById(R.id.btn_exit)
        result = findViewById(R.id.tv_show)

        btn_submit.setOnClickListener(this)
        btn_clear.setOnClickListener(this)
        btn_exit.setOnClickListener(this)

        if (savedInstanceState != null){
            val hasil = savedInstanceState.getString(STATE_RESULT) as String
            result.text = hasil
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MainActivity.STATE_RESULT, result.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_submit) {
            val inNama = edt_nama.text.trim()
            val inTinggi = edt_tinggi.text.toString().trim()
            val inBb = edt_bb.text.toString().trim()

            val drop_jk = findViewById(R.id.jenis_kelamin) as Spinner
            val value_jk = drop_jk.selectedItem.toString()
            Toast.makeText(applicationContext, value_jk, Toast.LENGTH_SHORT).show()

            val bbDouble : Double = inBb.toDouble()
            val tbDouble : Double = inTinggi.toDouble()

            val bmi = bbDouble/(tbDouble*tbDouble/10000)

            var showBMI : String = ""
            var bbIdeal : Double = 0.0

            if (bmi < 18.5){
                showBMI = "Kurus"
            } else if (bmi >= 18.5 && bmi <= 24.9){
                showBMI = "Normal"
            } else if (bmi >= 25 && bmi <= 29.9){
                showBMI = "Overweight"
            } else if (bmi >= 30){
                showBMI = "Obesitas"
            }

            when(value_jk){
                "Pria" -> bbIdeal = ((tbDouble-100)-((tbDouble-100)*10/100))
                "Wanita" -> bbIdeal = ((tbDouble-100)-((tbDouble-100)*15/100))
            }

            var emptyField = false
            var invalidField = false

            if (TextUtils.isEmpty(inNama)){
                emptyField = true
                edt_nama.setError("Field tak boleh kosong")
            }

            if (TextUtils.isEmpty(inTinggi)){
                emptyField = true
                edt_tinggi.setError("Field tak boleh kosong")
            }

            if (TextUtils.isEmpty(inBb)){
                emptyField = true
                edt_bb.setError("Field tak boleh kosong")
            }

            if (inNama == null){
                invalidField = true
                edt_nama.setError("Isi field dengan yang sesuai")
            }

            if (inTinggi == null){
                invalidField = true
                edt_tinggi.setError("Isi field dengan yang sesuai")
            }

            if (inBb == null){
                invalidField = true
                edt_bb.setError("Isi field dengan yang sesuai")
            }

            if (value_jk == "Pilih"){
                invalidField = true
            }

            if(!emptyField && !invalidField){
                result.setText("Hasil : \n-----------------" +
                        "\nNama: ${inNama} " +
                        "\nTinggi: ${inTinggi} cm" +
                        "\nBerat Badan: ${inBb} kg" +
                        "\nJenis Kelamin ${value_jk} " +
                        "\nNilai BMI: ${bmi}" +
                        "\nBMI: ${showBMI} " +
                        "\nBerat Ideal: ${bbIdeal} kg" )
            }
        }

        if (v?.id == R.id.btn_clear){
            result.setText("")
        }

        if (v?.id == R.id.btn_exit){
            System.exit(0)
        }
    }
}