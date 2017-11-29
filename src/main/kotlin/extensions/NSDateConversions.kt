import platform.Foundation.*

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