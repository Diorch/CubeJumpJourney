package com.example.cubejump.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences

object PreferenceManager {
    private const val PREFS_NAME = "cube_jump_journey_prefs"
    private const val KEY_HIGH_LEVEL = "high_level"

    private val prefs: Preferences by lazy {
        Gdx.app.getPreferences(PREFS_NAME)
    }

    fun getHighLevel(): Int {
        return prefs.getInteger(KEY_HIGH_LEVEL, 1)
    }

    fun saveHighLevel(level: Int) {
        val currentHigh = getHighLevel()
        if (level > currentHigh) {
            prefs.putInteger(KEY_HIGH_LEVEL, level)
            prefs.flush()
        }
    }
}
