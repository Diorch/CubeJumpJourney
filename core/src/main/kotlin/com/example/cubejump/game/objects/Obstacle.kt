package com.example.cubejump.game.objects

import com.badlogic.gdx.math.Rectangle

enum class ObstacleType {
    SQUARE, TRIANGLE
}

class Obstacle(
    val x: Float, 
    val y: Float, 
    val width: Float, 
    val height: Float,
    val type: ObstacleType = ObstacleType.SQUARE
) {
    val bounds = Rectangle(x, y, width, height)
}
