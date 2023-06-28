package androidx.compose.material3

import kotlin.test.Test
import kotlin.test.assertEquals

class ExtTest {

    @Test
    fun stringFormatTest() {
        val expect = "http://www.aaa.com/igrf/13/?latitude=59.127935&longitude=38.005035&altitude=0&date=2020-01-03&format=json"
        val actual = "http://www.%s.com/igrf/13/?latitude=%f&longitude=%f&altitude=0&date=%d-%02d-%02d&format=json".formatString(
            "aaa",
            59.127934932762166,
            38.00503518930868,
            2020,
            1,
            3,
        )
        assertEquals(expect, actual)
    }

    @Test
    fun stringFormat2Test() {
        val expect = "My name is b a."
        val actual = "My name is %2\$s %1\$s.".formatString("a", "b")
        assertEquals(expect, actual)
    }
}
