package com.seiko.greenqrscanner.util

import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding

fun String.nsData(): NSData {
    return NSString.create(string = this)
        .dataUsingEncoding(NSUTF8StringEncoding)!!
}
