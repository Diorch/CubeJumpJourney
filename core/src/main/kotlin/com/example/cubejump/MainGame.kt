package com.example.cubejump

import com.example.cubejump.assets.AssetLoader
import com.example.cubejump.screens.MainMenuScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.async.KtxAsync

class MainGame : KtxGame<KtxScreen>() {
    val assetLoader = AssetLoader()

    override fun create() {
        KtxAsync.initiate()
        assetLoader.loadAll()

        addScreen(MainMenuScreen(this))
        setScreen<MainMenuScreen>()
    }


    override fun dispose() {
        super.dispose()
        assetLoader.dispose()
    }
}