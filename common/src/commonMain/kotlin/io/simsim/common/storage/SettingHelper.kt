package io.simsim.common.storage

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.simsim.Setting
import io.simsim.common.storage.db.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.map

object SettingHelper : CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Default) {
    private val db = getDatabase()
    fun getAllSettings() = db.settingDaoQueries.seleteAll()
        .asFlow()
        .mapToList(this.coroutineContext)
        .map { settings ->
            settings.associate { setting ->
                setting.settingKey to setting.settingValue
            }
        }

    fun getSetting(settingKey: String) = db.settingDaoQueries.getSettingVaule(settingKey).executeAsOneOrNull() ?: ""

    fun upsertSetting(setting: Setting) = db.settingDaoQueries.upsert(setting)

    fun upsetSettings(settings: List<Setting>) = settings.forEach(::upsertSetting)
}