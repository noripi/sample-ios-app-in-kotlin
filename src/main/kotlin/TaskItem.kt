import platform.Foundation.*

const val TASK_DATE_FORMAT = "yyyy.MM.dd HH:mm"

data class TaskItem(val title: String, val deadline: NSDate)