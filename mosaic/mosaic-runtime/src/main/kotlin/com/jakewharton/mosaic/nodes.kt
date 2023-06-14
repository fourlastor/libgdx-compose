package com.jakewharton.mosaic

import androidx.compose.runtime.AbstractApplier
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont

internal sealed class MosaicNode {
	abstract fun draw(batch: Batch)
}

internal class TextNode(private val font: BitmapFont, initialValue: String = "") : MosaicNode() {

	var value: String = initialValue
	var x: Float = 0f
	var y: Float = 0f

	override fun draw(batch: Batch) {
		font.draw(batch, value, x, y)
	}

	override fun toString() = "Text(\"$value\")"
}

internal class BoxNode : MosaicNode() {
	val children = mutableListOf<MosaicNode>()

	override fun draw(batch: Batch) {
		for (child in children) {
			child.draw(batch)
		}
	}

	override fun toString() = children.joinToString(prefix = "Box(", postfix = ")")
}

internal class MosaicNodeApplier(root: BoxNode) : AbstractApplier<MosaicNode>(root) {
	override fun insertTopDown(index: Int, instance: MosaicNode) {
		// Ignored, we insert bottom-up.
	}

	override fun insertBottomUp(index: Int, instance: MosaicNode) {
		val boxNode = current as BoxNode
		boxNode.children.add(index, instance)
	}

	override fun remove(index: Int, count: Int) {
		val boxNode = current as BoxNode
		boxNode.children.remove(index, count)
	}

	override fun move(from: Int, to: Int, count: Int) {
		val boxNode = current as BoxNode
		boxNode.children.move(from, to, count)
	}

	override fun onClear() {
		val boxNode = root as BoxNode
		boxNode.children.clear()
	}
}
