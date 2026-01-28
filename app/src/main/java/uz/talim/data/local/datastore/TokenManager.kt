package uz.talim.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(private val dataStore: DataStore<Preferences>) {
    private val accessTokenKey = stringPreferencesKey("access_token")

    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[accessTokenKey]
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[accessTokenKey] = token
        }
    }
}
