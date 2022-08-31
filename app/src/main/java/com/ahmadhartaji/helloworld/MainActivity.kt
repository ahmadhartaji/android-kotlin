package com.ahmadhartaji.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val berat = binding.editTextBerat.text.toString().toFloat()
        val tinggi = binding.editTextTinggi.text.toString().toFloat() / 100
        val rumusBMI = berat / (tinggi * tinggi)

        val selectedId = binding.radioGroup.checkedRadioButtonId
        val isMale = selectedId == R.id.radioButtonPria

        binding.textViewHasil.text = getString(R.string.bmi_x, rumusBMI)
        binding.textViewKategori.text =
            getString(R.string.kategori_x, getKategori(rumusBMI, isMale))
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