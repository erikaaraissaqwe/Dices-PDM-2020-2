package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityConfigBinding
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.Config

class ConfigActivity : AppCompatActivity(){
    private lateinit var  activityConfigBinding: ActivityConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityConfigBinding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(activityConfigBinding.root)

        var oldConfig: Config? = intent.getParcelableExtra(MainActivity.Extras.NEW_CONFIG)
        if (oldConfig == null) {
            oldConfig = Config()
        }
        val radioGroup = activityConfigBinding.qtdDadosRg
        (radioGroup.getChildAt(oldConfig.qtdDados-1) as RadioButton).isChecked = true

        activityConfigBinding.salvarBt.setOnClickListener {

            val novaConfig = Config(
                    findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString().toInt(),
            )

            val retornoIntent = Intent()
            retornoIntent.putExtra(MainActivity.Extras.NEW_CONFIG, novaConfig)
            setResult(RESULT_OK, retornoIntent)
            finish()
        }
    }
}