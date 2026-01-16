package com.example.cubejump.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import ktx.assets.disposeSafely

import com.badlogic.gdx.assets.loaders.SkinLoader
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class AssetLoader {
    val assetManager = AssetManager()

    init {
        assetManager.setLoader(Skin::class.java, SkinLoader(assetManager.fileHandleResolver))
    }

    fun loadAll() {
        assetManager.load(AssetDescriptors.PLAYER)
        assetManager.load(AssetDescriptors.SPIKE)
        assetManager.load(AssetDescriptors.GROUND)
        assetManager.load(AssetDescriptors.UI_SKIN)
        
        assetManager.finishLoading()
        
        // Set filters for better quality
        assetManager.get(AssetDescriptors.PLAYER).setFilter(TextureFilter.Linear, TextureFilter.Linear)
        assetManager.get(AssetDescriptors.SPIKE).setFilter(TextureFilter.Linear, TextureFilter.Linear)
        assetManager.get(AssetDescriptors.GROUND).setFilter(TextureFilter.Linear, TextureFilter.Linear)
    }
    
    operator fun <T> get(descriptor: com.badlogic.gdx.assets.AssetDescriptor<T>): T {
        return assetManager.get(descriptor)
    }

    fun dispose() {
        assetManager.disposeSafely()
    }
}