package br.edu.ifsp.scl.ads.s5.pdm.dices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    fun onClick(view: View) {
        if (view == activityMainBinding.mensagemTv) {
            val resultado: Int = Random(System.currentTimeMillis()).nextInt(6) + 1
            activityMainBinding.resultadoTv.text = resultado.toString()
            // Gerando nome da imagem
            val resultadoImagem = "dice_$resultado"
            activityMainBinding.resultadoIv.setImageResource(
                resources.getIdentifier(resultadoImagem, "drawable", packageName)
            )
        }
    }
}