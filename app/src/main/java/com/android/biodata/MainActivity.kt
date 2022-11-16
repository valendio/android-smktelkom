package com.android.barvolume
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.barvolume.R
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edt_namaDep : EditText
    private lateinit var edt_namaBel : EditText
    private lateinit var edt_tanggal : EditText
    private lateinit var edt_telepon : EditText
    private lateinit var result: TextView
    private lateinit var btn_submit: Button
    private lateinit var btn_date: Button

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_namaDep = findViewById(R.id.edt_nama_depan)
        edt_namaBel = findViewById(R.id.edt_nama_belakang)
        edt_tanggal = findViewById(R.id.in_date)
        edt_telepon = findViewById(R.id.edt_telepon)
        result = findViewById(R.id.result)
        btn_submit = findViewById(R.id.btn_submit)
        btn_date = findViewById(R.id.btn_date)

        btn_date.setOnClickListener(this);
        btn_submit.setOnClickListener(this)

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
        if (v?.id == R.id.btn_submit){
            val inputNamaDepan = edt_namaDep.text.trim()
            val inputNamaBelakang = edt_namaBel.text.trim()
            val inputTanggal = edt_tanggal.text.toString().trim()
            val inputTelepon = edt_telepon.text.toString().trim()

            var emptyField = false
            var invalidField = false

            if (TextUtils.isEmpty(inputNamaDepan)){
                emptyField = true
                edt_namaDep.setError("Field tak boleh kosong")
            }

            if (TextUtils.isEmpty(inputNamaBelakang)){
                emptyField = true
                edt_namaBel.setError("Field tak boleh kosong")
            }

            if (TextUtils.isEmpty(inputTanggal)){
                emptyField = true
                edt_tanggal.setError("Field tak boleh kosong")
            }

            if (TextUtils.isEmpty(inputTelepon)){
                emptyField = true
                edt_telepon.setError("Field tak boleh kosong")
            }

            if (inputNamaDepan == null){
                invalidField = true
                edt_namaDep.setError("Isi field dengan yang sesuai")
            }

            if (inputNamaBelakang == null){
                invalidField = true
                edt_namaBel.setError("Isi field dengan yang sesuai")
            }

            if (inputTanggal == null){
                invalidField = true
                edt_tanggal.setError("Isi field dengan yang sesuai")
            }

            if (inputTelepon == null){
                invalidField = true
                edt_telepon.setError("Isi field dengan yang sesuai")
            }

            if(!emptyField && !invalidField){
                result.setText("Nama Lengkap: ${inputNamaDepan} ${inputNamaBelakang}\nTanggal Lahir: ${inputTanggal} \nNomor Telepon: ${inputTelepon}")

            }
        }

        if (v?.id == R.id.btn_date) {

            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> edt_tanggal.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
    }
}