package com.example.cubejump.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture

import com.badlogic.gdx.scenes.scene2d.ui.Skin

object AssetDescriptors {
    val PLAYER = AssetDescriptor("player.png", Texture::class.java)
    val SPIKE = AssetDescriptor("spike.png", Texture::class.java)
    val GROUND = AssetDescriptor("ground.png", Texture::class.java)
    val UI_SKIN = AssetDescriptor("ui/uiskin.json", Skin::class.java)
}