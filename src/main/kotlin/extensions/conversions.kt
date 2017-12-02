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

fun String.toNSDate(format: String = "yyyy-MM-dd HH:mm:ss ZZZ"): NSDate? {
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
 * Map -> NSDictionary
 */
fun <K : NSCopyingProtocol, V : ObjCObject> Map<K, V>.toNSDictionary(): NSDictionary {
    val mutable = NSMutableDictionary.dictionaryWithCapacity(this.size.toLong())
    this.forEach { (key, value) ->
        mutable[key] = value
    }
    return mutable
}

/**
 * List <-> NSArray
 */
fun <T : ObjCObject> List<T>.toNSArray(): NSArray {
    val mutable = NSMutableArray.arrayWithCapacity(this.size.toLong())
    this.forEach {
        mutable.addObject(it)
    }
    return mutable
}

fun <T : NSStringConvertible> List<T>.toNSArray(): NSArray {
    val mutable = NSMutableArray.arrayWithCapacity(this.size.toLong())
    this.forEach {
        mutable.addObject(it.toNSString())
    }
    return mutable
}

fun <T : NSStringBackConvertible<U>, U> NSArray.toList(companion: T): List<U> {
    val mutable = mutableListOf<U>()
    for (i in 0 until this.count) {
        companion.fromNSString(this[i].uncheckedCast<NSString>())?.let {
            mutable.add(it)
        }
    }
    return mutable
}
