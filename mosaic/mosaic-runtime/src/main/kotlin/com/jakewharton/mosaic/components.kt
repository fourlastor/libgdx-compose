package com.jakewharton.mosaic

import androidx.compose.runtime.*
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import kotlinx.coroutines.withContext
import ktx.async.KtxAsync

@Composable
fun Text(value: String, x: Float = 0f, y: Float = 0f) {

	val fontValue = createFont()

	val font = fontValue.value ?: return

	ComposeNode<TextNode, MosaicNodeApplier>(
		factory = { TextNode(font) },
		update = {
			set(value) { this.value = value }
			set(x) { this.x = x }
			set(y) { this.y = y }
		}
	)
}

@Composable
private fun createFont() = produceState<BitmapFont?>(null) {
	this.value = withContext(KtxAsync.coroutineContext) {
		BitmapFont().apply { color = Color.RED }
	}
}


@Composable
fun Row(children: @Composable () -> Unit) {
	Box(true, children)
}

@Composable
fun Column(children: @Composable () -> Unit) {
	Box(false, children)
}

@Composable
private fun Box(isRow: Boolean, children: @Composable () -> Unit) {
	ComposeNode<BoxNode, MosaicNodeApplier>(
		factory = ::BoxNode,
		update = {},
		content = children,
	)
}
