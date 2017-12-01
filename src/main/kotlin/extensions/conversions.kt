package extensions

import kotlinx.cinterop.*
import platform.Foundation.*

/**
 * NSDate <-> String
 */
fun NSDate.format(format: String): String {
    val formatter = NSDateFormatter().apply {
        this.dateFormat = format
    }

    return formatter.stringFromDate(this)
}

fun String.toNSDate(format: String): NSDate? {
    val formatter = NSDateFormatter().apply {
        this.dateFormat = format
    }

    return formatter.dateFromString(this)
}

/**
 * String -> NSString
 */
fun String.toNSString(): NSString {
    return interpretObjCPointer<NSString>(CreateNSStringFromKString(this))
}

/**
 * Int -> NSNumber
 */
fun Int.toNSNumber(): NSNumber {
    return NSNumber.numberWithInt(this)
}

/**
 * Map -> Dictionary
 */
fun <K : NSCopyingProtocol, V : ObjCObject> Map<K, V>.toNSDictionary(): NSDictionary {
    val mutable = NSMutableDictionary.dictionaryWithCapacity(this.size.toLong())
    this.forEach { (key, value) ->
        mutable[key] = value
    }
    return mutable
}
