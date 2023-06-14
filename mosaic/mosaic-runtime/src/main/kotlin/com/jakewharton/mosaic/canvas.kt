package com.jakewharton.mosaic

internal interface GameCanvas {
	val width: Int
	val height: Int
	val lines: MutableList<String>

	fun write(
		string: String,
	) {
		lines.add(string)
	}

	fun render(): List<String> = lines
}

internal class GameSurface(
	override val width: Int,
	override val height: Int,
) : GameCanvas {
	override val lines = mutableListOf<String>()
}
