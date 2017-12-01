import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*
import extensions.*

const val MAIN_STORYBOARD_NAME = "Main"
const val TASK_LIST_UD_KEY = "TASK_LIST"

var taskList: MutableList<TaskItem> = mutableListOf()

class AppDelegate : UIResponder(), UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta {}

    override fun init() = initBy(AppDelegate())

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: NSDictionary?): Boolean {
        NSUserDefaults.standardUserDefaults.registerDefaults(mapOf(
                TASK_LIST_UD_KEY.toNSString() to listOf<TaskItem>().toNSArray()
        ).toNSDictionary())

        val taskListHoge: List<TaskItem> = NSUserDefaults.standardUserDefaults.arrayForKey(
                TASK_LIST_UD_KEY)?.toList(TaskItem.Companion) ?: listOf()
        println(taskListHoge)

        taskList = mutableListOf()

        return true
    }

    override fun applicationWillTerminate(application: UIApplication) {
        NSUserDefaults.standardUserDefaults.setObject(taskList.toNSArray(),
                forKey = TASK_LIST_UD_KEY)
    }

    override fun applicationDidEnterBackground(application: UIApplication) {
        NSUserDefaults.standardUserDefaults.setObject(taskList.toNSArray(),
                forKey = TASK_LIST_UD_KEY)
    }
}
