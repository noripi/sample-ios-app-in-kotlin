package extensions

import platform.Foundation.*

interface NSStringConvertible {
    fun toNSString(): NSString
}

interface NSStringBackConvertible<T> {
    fun fromNSString(string: NSString): T?
}