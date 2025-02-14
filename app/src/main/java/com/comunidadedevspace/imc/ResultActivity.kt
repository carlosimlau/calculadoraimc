package com.comunidadedevspace.imc

import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_IMC = "ResultActivity.KEY_IMC"

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(R.layout.activity_result)


        val result = intent.getFloatExtra(KEY_RESULT_IMC, 0f)

        // Referências aos componentes do layout
        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvClassificacao = findViewById<TextView>(R.id.tv_classificacao)
        val tvDica = findViewById<TextView>(R.id.tv_dica1)


        tvResult.text = String.format("%.2f", result)


        val (classificacao, cor, dica) = when {
            result < 18.5 -> Triple(
                "Magreza",
                ContextCompat.getColor(this, R.color.azul),
                "Aumente a ingestão calórica com alimentos saudáveis e faça acompanhamento médico."
            )
            result in 18.5..24.9 -> Triple(
                "Normal",
                ContextCompat.getColor(this, R.color.verde),
                "Parabéns! Continue mantendo um estilo de vida equilibrado."
            )
            result in 25.0..29.9 -> Triple(
                "Sobrepeso",
                ContextCompat.getColor(this, R.color.amarelo),
                "Tente equilibrar sua alimentação e aumentar a atividade física."
            )
            result in 30.0..39.9 -> Triple(
                "Obesidade",
                ContextCompat.getColor(this, R.color.laranja),
                "Considere um plano alimentar saudável e consulte um especialista."
            )
            else -> Triple(
                "Obesidade Grave",
                ContextCompat.getColor(this, R.color.vermelho),
                "Procure orientação médica para um tratamento adequado e saudável."
            )
        }


        tvClassificacao.text = classificacao
        tvClassificacao.setTextColor(cor)
        tvDica.text = dica


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }
    }
}
