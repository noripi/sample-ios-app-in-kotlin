import platform.Foundation.*
import extensions.*

const val TASK_DATE_FORMAT = "yyyy.MM.dd HH:mm"

data class TaskItem(val title: String, val deadline: NSDate) : NSStringConvertible {
    override fun toNSString(): NSString {
        return this.toString().toNSString()
    }

    companion object : NSStringBackConvertible<TaskItem> {
        private val OBJECT_PATTERN = NSRegularExpression.regularExpressionWithPattern(
                "TaskItem\\(title=(.+?), deadline=(.+?)\\)", options = 0, error = null)!!

        override fun fromNSString(string: NSString): TaskItem? {
            val result = OBJECT_PATTERN.firstMatchInString(string.toString(), options = 0,
                    range = NSMakeRange(0, string.length)) ?: return null

            val title = string.substringWithRange(result.rangeAtIndex(1))
            val deadline = string.substringWithRange(
                    result.rangeAtIndex(2)).toNSDate() ?: return null

            return TaskItem(title, deadline)
        }
    }
}

