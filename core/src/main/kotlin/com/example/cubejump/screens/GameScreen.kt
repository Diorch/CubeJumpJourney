package com.example.cubejump.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.example.cubejump.MainGame
import com.example.cubejump.assets.AssetDescriptors
import com.example.cubejump.game.WorldController
import com.example.cubejump.game.WorldRenderer
import com.example.cubejump.game.objects.Level
import com.example.cubejump.game.objects.PlayerState
import ktx.actors.onChange
import ktx.app.clearScreen

class GameScreen(game: MainGame) : BaseScreen(game) {
    
    private val level = Level()
    private val controller = WorldController(level)
    private val renderer = WorldRenderer(level, game.assetLoader)
    
    // UI
    private val stage = Stage(ScreenViewport())
    private val skin: Skin = game.assetLoader[AssetDescriptors.UI_SKIN]
    private val gameOverTable = Table()
    private lateinit var levelLabel: Label
    private var isGameOverShown = false

    override fun show() {
        level.load(1)
        
        // Ensure InputProcessor is set to Stage so buttons work
        Gdx.input.inputProcessor = stage
        
        setupUI()
    }

    private fun setupUI() {
        // Clear previous actors if any (though show() is usually called once per instance if not reused)
        stage.clear()

        // --- HUD ---
        val hudTable = Table()
        hudTable.setFillParent(true)
        hudTable.top().left()
        
        levelLabel = Label("Level: 1", skin)
        levelLabel.color = Color.BLACK
        hudTable.add(levelLabel).pad(20f)
        
        // --- Game Over Menu ---
        gameOverTable.setFillParent(true)
        gameOverTable.center()
        gameOverTable.isVisible = false
        // Add a semi-transparent background to the table? 
        // For now just buttons over the game view
        
        val gameOverLabel = Label("GAME OVER", skin)
        gameOverLabel.setFontScale(2f)
        gameOverLabel.color = Color.RED
        
        val retryButton = TextButton("RETRY", skin)
        retryButton.onChange { 
            level.load(level.currentLevel)
            hideGameOver()
        }
        
        val menuButton = TextButton("MENU", skin)
        menuButton.onChange {
            game.addScreen(MainMenuScreen(game))
            game.setScreen<MainMenuScreen>()
            game.removeScreen<GameScreen>()
            dispose()
        }
        
        gameOverTable.add(gameOverLabel).padBottom(30f).row()
        gameOverTable.add(retryButton).width(150f).height(50f).padBottom(15f).row()
        gameOverTable.add(menuButton).width(150f).height(50f)

        stage.addActor(hudTable)
        stage.addActor(gameOverTable)
    }
    
    private fun hideGameOver() {
        isGameOverShown = false
        gameOverTable.isVisible = false
    }

    override fun render(delta: Float) {
        // Update Logic
        controller.update(delta)
        
        // Update HUD info
        levelLabel.setText("Level: ${level.currentLevel}")
        
        // Check Game Over State
        if (level.player.state == PlayerState.DEAD && !isGameOverShown) {
            isGameOverShown = true
            gameOverTable.isVisible = true
        }
        
        // Render Game World
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        renderer.render()
        
        // Render UI
        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        super.dispose()
        renderer.dispose()
        stage.dispose()
    }
}