import platform.Foundation.*
import extensions.*

const val TASK_DATE_FORMAT = "yyyy.MM.dd HH:mm"

data class TaskItem(val title: String, val deadline: NSDate) : NSStringConvertible {
    override fun toNSString(): NSString {
        return this.toString().toNSString()
    }

    companion object : NSStringBackConvertible<TaskItem> {
        override fun fromNSString(string: NSString): TaskItem {
            return TaskItem("", NSDate.date())
        }
    }
}

