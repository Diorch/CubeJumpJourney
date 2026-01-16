package com.example.cubejump.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.example.cubejump.MainGame
import com.example.cubejump.assets.AssetDescriptors
import com.example.cubejump.utils.PreferenceManager
import ktx.app.clearScreen
import ktx.actors.onChange

class MainMenuScreen(game: MainGame) : BaseScreen(game) {
    private val stage = Stage(ScreenViewport())
    private val skin: Skin = game.assetLoader[AssetDescriptors.UI_SKIN]

    override fun show() {
        Gdx.input.inputProcessor = stage
        setupUI()
    }

    private fun setupUI() {
        val table = Table()
        table.setFillParent(true)
        // table.debug = true // Turn on to verify layout

        val title = Label("CUBE JUMP JOURNEY", skin)
        title.setFontScale(2f)
        title.color = Color.GOLD

        val highLevel = PreferenceManager.getHighLevel()
        val scoreLabel = Label("Best Level Reached: $highLevel", skin)
        scoreLabel.color = Color.WHITE

        val playButton = TextButton("PLAY GAME", skin)
        playButton.onChange { 
            game.addScreen(GameScreen(game))
            game.setScreen<GameScreen>()
            game.removeScreen<MainMenuScreen>()
            dispose()
        }

        val exitButton = TextButton("EXIT", skin)
        exitButton.onChange {
            Gdx.app.exit()
        }

        table.add(title).padBottom(20f).row()
        table.add(scoreLabel).padBottom(40f).row()
        table.add(playButton).width(200f).height(60f).padBottom(20f).row()
        table.add(exitButton).width(200f).height(60f).row()

        stage.addActor(table)
    }

    override fun render(delta: Float) {
        clearScreen(0.2f, 0.2f, 0.2f)
        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
        super.dispose()
    }
}
