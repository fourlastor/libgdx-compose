package example

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jakewharton.mosaic.Text
import com.jakewharton.mosaic.runMosaic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runMosaic {
	var count: Int by mutableStateOf(0)

	setContent {
		Text("First: $count", x = 200f, y = 200f)
		Text("Second: $count", x = 200f, y = 200f + 10f * count)
	}

	for (i in 1..20) {
		delay(500)
		count = i
	}
}
