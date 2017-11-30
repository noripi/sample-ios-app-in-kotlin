import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

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
}