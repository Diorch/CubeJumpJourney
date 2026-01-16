package com.example.cubejump.game.objects

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.example.cubejump.utils.GameConfig

enum class PlayerState {
    RUNNING, JUMPING, FALLING, DEAD
}

class Player {
    val position = Vector2(GameConfig.PLAYER_START_X, GameConfig.PLAYER_START_Y)
    val velocity = Vector2(GameConfig.PLAYER_MOVE_SPEED, 0f)
    val bounds = Rectangle(position.x, position.y, GameConfig.PLAYER_WIDTH, GameConfig.PLAYER_HEIGHT)
    var state = PlayerState.RUNNING

    fun update(deltaTime: Float) {
        if (state == PlayerState.DEAD) return

        // Apply Gravity
        velocity.y += GameConfig.GRAVITY * deltaTime
        
        // Update Position
        // Note: x-velocity is constant in this game design (runner), 
        // but we might want to control it via WorldController instead.
        // For now, we update position based on velocity.
        position.mulAdd(velocity, deltaTime)
        
        // Update Bounds
        bounds.setPosition(position)
        
        // Simple floor collision for testing before obstacles
        if (position.y < 0) {
            position.y = 0f
            velocity.y = 0f
            state = PlayerState.RUNNING
            bounds.setPosition(position)
        }
    }

    fun jump() {
        if (state == PlayerState.RUNNING) {
            velocity.y = GameConfig.PLAYER_JUMP_SPEED
            state = PlayerState.JUMPING
        }
    }

    fun die() {
        state = PlayerState.DEAD
        velocity.set(0f, 0f)
    }
}
