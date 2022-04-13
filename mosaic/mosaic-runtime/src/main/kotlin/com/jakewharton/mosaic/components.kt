package com.jakewharton.mosaic

import androidx.compose.runtime.*

@Composable
fun Text(value: String, x: Float = 0f, y: Float = 0f) {
	ComposeNode<TextNode, MosaicNodeApplier>(
		factory = { TextNode() },
		update =  {
			set(value) { this.value = value }
			set(x) { this.x = x }
			set(y) { this.y = y }
		}
	)
}

@Immutable
class Color private constructor(
	internal val fg: Int,
	internal val bg: Int,
) {
	companion object {
		@Stable
		val Black = Color(30, 40)
		@Stable
		val Red = Color(31, 41)
		@Stable
		val Green = Color(32, 42)
		@Stable
		val Yellow = Color(33, 43)
		@Stable
		val Blue = Color(34, 44)
		@Stable
		val Magenta = Color(35, 45)
		@Stable
		val Cyan = Color(36, 46)
		@Stable
		val White = Color(37, 47)
		@Stable
		val BrightBlack = Color(90, 100)
		@Stable
		val BrightRed = Color(91, 101)
		@Stable
		val BrightGreen = Color(92, 102)
		@Stable
		val BrightYellow = Color(93, 103)
		@Stable
		val BrightBlue = Color(94, 104)
		@Stable
		val BrightMagenta = Color(95, 105)
		@Stable
		val BrightCyan = Color(96, 106)
		@Stable
		val BrightWhite = Color(97, 107)
	}
}

@Immutable
class TextStyle private constructor(
	internal val bits: Int,
) {
	operator fun plus(other: TextStyle) = TextStyle(bits or other.bits)
	operator fun contains(style: TextStyle) = (style.bits and bits) != 0

	companion object {
		@Stable
		val None = TextStyle(0)
		@Stable
		val Underline = TextStyle(1)
		@Stable
		val Strikethrough = TextStyle(2)
		@Stable
		val Bold = TextStyle(4)
		@Stable
		val Dim = TextStyle(8)
		@Stable
		val Italic = TextStyle(16)
		@Stable
		val Invert = TextStyle(32)
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
