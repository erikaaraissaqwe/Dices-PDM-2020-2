package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import br.edu.ifsp.scl.ads.s5.pdm.dices.MainActivity.Extras.NEW_CONFIG
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.Config
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding : ActivityMainBinding

    private lateinit var confiController : ConfigController

    private lateinit var config : Config

    private val CONFIG_REQUEST_CODE = 0

    object Extras {
        val NEW_CONFIG = "NEW_CONFIG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        confiController = ConfigController(this)
        config = confiController.getSavedConfig()

    }

    fun onClick(view: View) {
        if (view == activityMainBinding.jogarBt) {
            val resultado: Int = Random(System.currentTimeMillis()).nextInt(6) + 1
            var textResult = resultado.toString()
            activityMainBinding.resultadoTv.text = textResult

            val resultadoImagem = "dice_$resultado"
            activityMainBinding.resultadoIv.setImageResource(
                    resources.getIdentifier(resultadoImagem, "drawable", packageName)
            )

            if (config.qtdDados == 2) {
                activityMainBinding.resultadoIv2.visibility = VISIBLE

                val resultado: Int = Random(System.currentTimeMillis()).nextInt(config.qtdFaces) + 1
                textResult += "    e    $resultado"

                val resultadoImagem = "dice_$resultado"
                activityMainBinding.resultadoIv2.setImageResource(
                        resources.getIdentifier(resultadoImagem, "drawable", packageName)
                )
            } else {
                activityMainBinding.resultadoIv2.visibility = INVISIBLE
            }
            activityMainBinding.resultadoTv.text = textResult
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            if (item.itemId == R.id.configuracoesMi){
                val configIntent = Intent(this, ConfigActivity::class.java)
                configIntent.putExtra(NEW_CONFIG, config)
                startActivityForResult(configIntent, CONFIG_REQUEST_CODE)
                true
            }
            else
                false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CONFIG_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val confg = data.getParcelableExtra<Config>(NEW_CONFIG)
            if (confg != null){
                confiController.saveConfig(confg)
                this.config = (confg)
            }
        }
    }
}