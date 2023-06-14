package com.jakewharton.mosaic


internal class GdxOutput(
	private val application: ComposeApplication,
) {

	fun display(state: MosaicNode) {
		application.refresh(state)
	}
}
