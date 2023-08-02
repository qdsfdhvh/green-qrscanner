package com.seiko.greenqrscanner.util

import com.seiko.greenqrscanner.MR

inline fun stringResource(resource: String): String {
    return resource
}

inline val MR.strings get() = string
