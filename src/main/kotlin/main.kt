import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

const val MAIN_STORYBOARD_NAME = "Main"
var taskList: MutableList<TaskItem> = mutableListOf()

fun main(args: Array<String>) {
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("konan") + args).map { it.cstr.getPointer(memScope) }.toCValues()

        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(AppDelegate))
        }
    }
}
