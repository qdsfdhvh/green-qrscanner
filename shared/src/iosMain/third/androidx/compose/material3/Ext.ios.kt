package androidx.compose.material3

import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

// https://stackoverflow.com/questions/64495182/kotlin-native-ios-string-formatting-with-vararg
actual fun String.formatString(vararg args: Any?): String {
    val format = localized().replace("%s", "%@")
    // Kotlin does not support passing variadic parameters to Objective-C
    // We implement calling the method with up to 9 arguments which is enough in practice
    return when (args.size) {
        0 -> NSString.stringWithFormat(format)
        1 -> NSString.stringWithFormat(format, args[0])
        2 -> NSString.stringWithFormat(format, args[0], args[1])
        3 -> NSString.stringWithFormat(format, args[0], args[1], args[2])
        4 -> NSString.stringWithFormat(format, args[0], args[1], args[2], args[3])
        5 -> NSString.stringWithFormat(format, args[0], args[1], args[2], args[3], args[4])
        6 -> NSString.stringWithFormat(format, args[0], args[1], args[2], args[3], args[4], args[5])
        7 -> NSString.stringWithFormat(format, args[0], args[1], args[2], args[3], args[4], args[5], args[6])
        8 -> NSString.stringWithFormat(format, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7])
        9 -> NSString.stringWithFormat(format, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8])
        else -> error("Too many arguments.")
    }
}

fun String.localized(): String {
    val localizedString = NSBundle.mainBundle.localizedStringForKey(this, this, null)
    return if (localizedString != this) {
        localizedString
    } else {
        this
    }
}
