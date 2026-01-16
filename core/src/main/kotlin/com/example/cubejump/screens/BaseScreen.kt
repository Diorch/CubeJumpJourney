package com.example.cubejump.screens

import com.badlogic.gdx.scenes.scene2d.Stage
import com.example.cubejump.MainGame
import ktx.app.KtxScreen
import ktx.assets.disposeSafely

abstract class BaseScreen(val game: MainGame) : KtxScreen {
    // Common properties like Stage could go here
    // val stage: Stage = Stage()

    override fun dispose() {
        // stage.disposeSafely()
    }
}
