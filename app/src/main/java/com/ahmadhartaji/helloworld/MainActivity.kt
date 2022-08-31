package com.ahmadhartaji.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.ahmadhartaji.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHitung.setOnClickListener { hitung() }
    }

    private fun hitung() {
        val berat = binding.editTextBerat.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(this, "Berat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val tinggi = binding.editTextTinggi.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(this, "Tinggi tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Kategori tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val tinggiCm = tinggi.toFloat() / 100
        val rumusBMI = berat.toFloat() / (tinggiCm * tinggiCm)
        val isMale = selectedId == R.id.radioButtonPria
        val kategori = getKategori(rumusBMI, isMale)

        binding.textViewHasil.text = getString(R.string.bmi_x, rumusBMI)
        binding.textViewKategori.text = getString(R.string.kategori_x, kategori)
    }

    private fun getKategori(bmi: Float, isMale: Boolean): String {
        val stringRes = if (isMale) {
            when {
                bmi < 20.5 -> R.string.kurus
                bmi >= 27.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        } else {
            when {
                bmi < 18.5 -> R.string.kurus
                bmi >= 25.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        }
        return getString(stringRes)
    }
}