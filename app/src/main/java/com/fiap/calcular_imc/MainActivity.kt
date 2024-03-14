package com.fiap.calcular_imc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fiap.calcular_imc.ui.theme.Calcular_IMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val radioButtonMasculino = findViewById<RadioButton>(R.id.radioButton)
        val radioButtonFeminino = findViewById<RadioButton>(R.id.radioButton2)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            val alturaText = findViewById<TextView>(R.id.editTextNumberDecimal).text.toString()

            if (alturaText.isNotBlank()) {
                val altura = alturaText.toDouble()
                val pesoIdeal = if (radioButtonMasculino.isChecked) {
                    calcularPesoIdealHomem(altura)
                } else {
                    calcularPesoIdealMulher(altura)
                }
                Toast.makeText(this, "Seu peso ideal é: %.2f kg".format(pesoIdeal), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, insira uma altura válida.", Toast.LENGTH_SHORT).show()
            }
        }

        radioButtonMasculino.setOnClickListener {
            radioButtonFeminino.isChecked = false
        }

        radioButtonFeminino.setOnClickListener {
            radioButtonMasculino.isChecked = false
        }
    }

    private fun calcularPesoIdealHomem(altura: Double): Double {
        return (72.7 * altura) - 58
    }

    private fun calcularPesoIdealMulher(altura: Double): Double {
        return (62.1 * altura) - 44.7
    }
}