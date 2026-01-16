package com.example.cubejump

import com.example.cubejump.game.objects.Player
import com.example.cubejump.game.objects.PlayerState
import com.example.cubejump.utils.GameConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CoreLogicTest {

    @Test
    fun testPlayerInitialState() {
        val player = Player()
        assertEquals(PlayerState.RUNNING, player.state)
        assertEquals(GameConfig.PLAYER_START_X, player.position.x)
    }

    @Test
    fun testPlayerJump() {
        val player = Player()
        // Ensure valid state for jumping
        player.state = PlayerState.RUNNING
        
        player.jump()
        
        assertEquals(PlayerState.JUMPING, player.state)
        assertEquals(GameConfig.PLAYER_JUMP_SPEED, player.velocity.y)
    }

    @Test
    fun testPhysicsUpdate() {
        val player = Player()
        player.jump()
        val initialY = player.position.y
        val initialVy = player.velocity.y
        
        // Simulate one frame (0.1s)
        player.update(0.1f)
        
        // Check Gravity effect: Velocity should decrease
        assertTrue(player.velocity.y < initialVy, "Velocity should decrease due to gravity")
        
        // Check Movement: Position should change
        // y = y0 + vy * t (approx)
        assertTrue(player.position.y != initialY)
    }
}
