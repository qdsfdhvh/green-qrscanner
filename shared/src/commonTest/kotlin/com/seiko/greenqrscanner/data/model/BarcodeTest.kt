package com.seiko.greenqrscanner.data.model

import com.seiko.greenqrscanner.util.decodeJson
import com.seiko.greenqrscanner.util.encodeJson
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BarcodeTest {

    @Test
    fun textTest() {
        val barcode = Barcode(
            rawValue = "aaa111",
            format = BarcodeFormat.FORMAT_1D,
            type = BarcodeType.Text,
        )
        val barcodeJson = barcode.encodeJson()
        assertEquals(
            "{\"rawValue\":\"aaa111\",\"format\":\"1D\",\"type\":{\"type\":\"text\"}}",
            barcodeJson,
        )
        assertEquals(
            barcode,
            barcodeJson.decodeJson(),
        )
    }

    @Test
    fun wifiTest() {
        val barcode = Barcode(
            rawValue = "WIFI:S:111;T:WPA;P:222;;",
            format = BarcodeFormat.FORMAT_2D,
            type = BarcodeType.Wifi(
                ssid = "111",
                password = "222",
                wifiType = BarcodeType.Wifi.Type.WPA,
            ),
        )
        val barcodeJson = barcode.encodeJson()
        assertEquals(
            "{\"rawValue\":\"WIFI:S:111;T:WPA;P:222;;\",\"format\":\"2D\",\"type\":{\"type\":\"wifi\",\"ssid\":\"111\",\"password\":\"222\",\"wifiType\":\"WPA\"}}",
            barcodeJson,
        )
        assertEquals(
            barcode,
            barcodeJson.decodeJson(),
        )
    }
}
