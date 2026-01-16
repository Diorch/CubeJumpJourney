package com.example.cubejump.game.objects

import com.badlogic.gdx.utils.Array
import com.example.cubejump.utils.GameConfig

class Level {
    val player = Player()
    val obstacles = Array<Obstacle>()
    var currentLevel = 1
    
    fun load(levelNumber: Int) {
        currentLevel = levelNumber
        obstacles.clear()
        
        // Reset player
        player.position.set(GameConfig.PLAYER_START_X, GameConfig.PLAYER_START_Y)
        player.velocity.set(GameConfig.PLAYER_MOVE_SPEED, 0f)
        player.state = PlayerState.RUNNING
        player.bounds.setPosition(player.position)
        
        when (levelNumber) {
            1 -> loadLevel1()
            2 -> loadLevel2()
            3 -> loadLevel3()
            else -> loadLevel1()
        }
    }

    private fun loadLevel1() {
        // Level 1: Introduction - Simple jumps
        obstacles.add(Obstacle(10f, 0f, 1f, 1f))
        obstacles.add(Obstacle(16f, 0f, 1f, 1f))
        obstacles.add(Obstacle(24f, 0f, 1f, 1f))
        // End marker (visualized as a tall wall or just empty space after this)
    }

    private fun loadLevel2() {
        // Level 2: Timing - Double obstacles and varying gaps
        obstacles.add(Obstacle(8f, 0f, 1f, 1f))
        obstacles.add(Obstacle(14f, 0f, 1f, 1.5f)) // Taller
        obstacles.add(Obstacle(20f, 0f, 1f, 1f))
        obstacles.add(Obstacle(23f, 0f, 1f, 1f)) // Quick succession
        obstacles.add(Obstacle(30f, 0f, 1f, 1.5f))
    }

    private fun loadLevel3() {
        // Level 3: Precision - Wide gaps and tall obstacles
        obstacles.add(Obstacle(10f, 0f, 1f, 2f)) // Tall
        obstacles.add(Obstacle(18f, 0f, 1f, 1f))
        obstacles.add(Obstacle(24f, 0f, 1f, 1.5f))
        obstacles.add(Obstacle(29f, 0f, 1f, 2f)) // Tall
        obstacles.add(Obstacle(35f, 0f, 1.5f, 1f)) // Wide
    }
}
