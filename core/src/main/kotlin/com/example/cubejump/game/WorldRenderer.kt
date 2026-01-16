package com.example.cubejump.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import com.example.cubejump.assets.AssetDescriptors
import com.example.cubejump.assets.AssetLoader
import com.example.cubejump.game.objects.Level
import com.example.cubejump.game.objects.PlayerState
import com.example.cubejump.utils.GameConfig
import ktx.graphics.use

class WorldRenderer(
    private val level: Level,
    private val assetLoader: AssetLoader
) : Disposable {

    private val camera = OrthographicCamera()
    private val batch = SpriteBatch()

    init {
        camera.setToOrtho(false, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT)
    }

    fun render() {
        // Update camera
        camera.position.x = level.player.position.x + 6f
        camera.update()

        batch.projectionMatrix = camera.combined
        
        batch.use { b ->
            drawFloor(b)
            drawObstacles(b)
            drawPlayer(b)
        }
    }

    private fun drawPlayer(batch: SpriteBatch) {
        val player = level.player
        val texture = assetLoader[AssetDescriptors.PLAYER]
        
        if (player.state == PlayerState.DEAD) {
            batch.color = Color.GRAY
        } else {
            batch.color = Color.WHITE
        }

        batch.draw(
            texture,
            player.position.x, player.position.y,
            player.bounds.width, player.bounds.height
        )
        batch.color = Color.WHITE // Reset
    }

    private fun drawObstacles(batch: SpriteBatch) {
        val texture = assetLoader[AssetDescriptors.SPIKE]
        level.obstacles.forEach { obstacle ->
            batch.draw(
                texture,
                obstacle.x, obstacle.y,
                obstacle.width, obstacle.height
            )
        }
    }

    private fun drawFloor(batch: SpriteBatch) {
        val texture = assetLoader[AssetDescriptors.GROUND]
        // Draw floor tiles spanning the camera view
        val startX = (camera.position.x - camera.viewportWidth / 2).toInt()
        val endX = (camera.position.x + camera.viewportWidth / 2).toInt() + 2

        for (i in startX until endX) {
            batch.draw(texture, i.toFloat(), -1f, 1f, 1f)
        }
    }

    override fun dispose() {
        batch.dispose()
    }
}