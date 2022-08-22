package com.app.androidev.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LocalDataStore @Inject constructor(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    companion object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("accessTokenKey")
    }


    fun getAccessToken() = runBlocking {
        context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN] ?: ""
        }.first()
    }

    suspend fun setAccessToken(token: String) {
        context.dataStore.edit { settings ->
            settings[ACCESS_TOKEN] = token
        }
    }
}