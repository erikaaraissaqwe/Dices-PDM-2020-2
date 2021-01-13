package br.edu.ifsp.scl.ads.s5.pdm.dices

import br.edu.ifsp.scl.ads.s5.pdm.dices.model.Config
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.ConfigDao
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.ConfigPreferences

class ConfigController(mainActivity : MainActivity) {
    val configDao: ConfigDao
    init {
        configDao = ConfigPreferences(mainActivity)
    }

    fun getSavedConfig() = configDao.readConfig()
    fun saveConfig(config: Config) = configDao.saveConfig(config)
}