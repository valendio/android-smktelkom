package com.android.barvolume
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)
        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_calculate) {
            val inputLen = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyField = false
            var isInvalidDouble = false

            if (TextUtils.isEmpty(inputLen)) {
                isEmptyField = true
                edtLength.error = "Field Ini Tidak Boleh Kosong"
            }
            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyField = true
                edtWidth.error = "Field Ini Tidak Boleh Kosong"
            }
            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyField = true
                edtHeight.error = "Field Ini Tidak Boleh Kosong"
            }

            var length = toDouble(inputLen)
            var width = toDouble(inputWidth)
            var height = toDouble(inputHeight)

            if (length == null) {
                isInvalidDouble = true
                edtLength.error = "nilai tidak valid"
            }
            if (width == null) {
                isInvalidDouble = true
                edtWidth.error = "nilai tidak valid"
            }

            if (height == null) {
                isInvalidDouble = true
                edtHeight.error = "nilai tidak valid"
            }

            if (!isEmptyField && !isInvalidDouble) {
                val volume = height!!.toDouble() * length!!.toDouble() * width!!.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: java.lang.NumberFormatException) {
            null
        }
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }

}