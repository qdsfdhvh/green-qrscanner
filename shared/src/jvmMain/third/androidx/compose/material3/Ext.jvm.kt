package androidx.compose.material3

import java.util.Locale

actual fun String.formatString(vararg args: Any?): String {
    return format(Locale.getDefault(), *args)
}
