import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*
import extensions.*

const val MAIN_STORYBOARD_NAME = "Main"
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
                "test".toNSString() to 1.toNSNumber()
        ).toNSDictionary())

        return true
    }
}
