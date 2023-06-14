package com.jakewharton.mosaic

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import kotlinx.coroutines.runBlocking
import ktx.async.KtxAsync


class ComposeApplication(
	private val onAppReady: (ComposeApplication) -> Unit,
) : ApplicationAdapter() {

	private lateinit var batch: SpriteBatch
	private lateinit var font: BitmapFont

	private var state: MosaicNode? = null

	internal fun refresh(state: MosaicNode) {
		this.state = state
		Gdx.graphics.requestRendering()
	}

	override fun create() {
		Gdx.graphics.isContinuousRendering = false
		batch = SpriteBatch()
		font = BitmapFont()
		font.color = com.badlogic.gdx.graphics.Color.RED
		KtxAsync.initiate()
		onAppReady(this)
	}

	override fun dispose() {
		runBlocking(KtxAsync.coroutineContext) {
			batch.dispose()
			font.dispose()
		}
	}

	override fun render() {
		ScreenUtils.clear(0f, 0f, 0f, 1f)

		state?.also {
			batch.begin()
			it.draw(batch)
			batch.end()
		}
	}
}
