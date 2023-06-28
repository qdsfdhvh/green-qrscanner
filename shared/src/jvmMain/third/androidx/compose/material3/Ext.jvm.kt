package androidx.compose.material3

actual fun String.formatString(vararg args: Any?): String {
    return this
    // return format(Locale.getDefault(), *args)
}
