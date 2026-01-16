package com.example.cubejump.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Intersector
import com.example.cubejump.game.objects.Level
import com.example.cubejump.game.objects.PlayerState

class WorldController(private val level: Level) {

    private val LEVEL_LENGTH = 50f // Length of a level in meters

    fun update(deltaTime: Float) {
        handleInput()
        
        if (level.player.state != PlayerState.DEAD) {
            updateLevel(deltaTime)
            checkCollisions()
            checkLevelComplete()
        }
    }

    private fun handleInput() {
        val jumpRequested = Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        if (level.player.state != PlayerState.DEAD) {
            if (jumpRequested) {
                level.player.jump()
            }
        }
    }

    private fun updateLevel(deltaTime: Float) {
        level.player.update(deltaTime)
    }

    private fun checkCollisions() {
        val player = level.player
        
        // Optimize: Only check obstacles near the player
        for (obstacle in level.obstacles) {
            if (obstacle.x < player.position.x - 5) continue // Already passed
            if (obstacle.x > player.position.x + 5) break    // Too far ahead

            if (Intersector.overlaps(player.bounds, obstacle.bounds)) {
                player.die()
                // Gdx.app.log("Game", "Player Died!")
                break
            }
        }
    }

    private fun checkLevelComplete() {
        if (level.player.position.x > LEVEL_LENGTH) {
            // Level Complete! Move to next level
            // Gdx.app.log("Game", "Level Complete!")
            val nextLevel = if (level.currentLevel < 3) level.currentLevel + 1 else 1
            level.load(nextLevel)
        }
    }
}
