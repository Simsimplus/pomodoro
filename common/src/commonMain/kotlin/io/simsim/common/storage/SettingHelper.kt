package io.simsim.common.storage

import io.simsim.Setting
import io.simsim.common.storage.db.getDatabase

object SettingHelper {
    private val db = getDatabase()
    fun getAllSettings() = db.settingDaoQueries.seleteAll().executeAsList()

    fun upsertSetting(setting: Setting) = db.settingDaoQueries.upsert(setting)

    fun upsetSettings(settings: List<Setting>) = settings.forEach(::upsertSetting)
}