import kotlinx.cinterop.*
import kotlin.reflect.*
import platform.Foundation.*
import platform.UIKit.*
import platform.CoreGraphics.*
import extensions.*

@ExportObjCClass
@Suppress("CONFLICTING_OVERLOADS")
class ListViewController(aDecoder: NSCoder) : UIViewController(aDecoder),
                                              UITableViewDelegateProtocol,
                                              UITableViewDataSourceProtocol {

    @ObjCOutlet
    private lateinit var tableView: UITableView

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(ListViewController(aDecoder))

    override fun debugDescription() = this::class.simpleName!!

    override fun viewDidLoad() {
        super.viewDidLoad()

        this.title = "タスク一覧"
        this.navigationItem.rightBarButtonItem = UIBarButtonItem(
                title = "追加",
                style = UIBarButtonItemStylePlain,
                target = this,
                action = NSSelectorFromString("addButtonDidTap:")
        )
    }

    override fun viewWillAppear(animated: Boolean) {
        super.viewWillAppear(animated)

        this.tableView.reloadData()
    }

    override fun tableView(tableView: UITableView, numberOfRowsInSection: NSInteger): NSInteger {
        return taskList.size.toLong()
    }

    override fun tableView(tableView: UITableView, cellForRowAtIndexPath: NSIndexPath): UITableViewCell {
        val cell = tableView.dequeueReusableCellWithIdentifier(
                TaskItemTableViewCell::class.simpleName!!,
                cellForRowAtIndexPath).uncheckedCast<TaskItemTableViewCell>()

        val taskItem = taskList[cellForRowAtIndexPath.row.toInt()]
        cell.taskTitle = taskItem.title
        cell.taskDeadline = taskItem.deadline

        return cell
    }

    override fun tableView(tableView: UITableView, heightForRowAtIndexPath: NSIndexPath): CGFloat {
        return 80.0
    }

    override fun tableView(tableView: UITableView, didSelectRowAtIndexPath: NSIndexPath) {
        tableView.deselectRowAtIndexPath(didSelectRowAtIndexPath, animated = true)

        val storyboard = UIStoryboard.storyboardWithName(MAIN_STORYBOARD_NAME, bundle = null)
        val editViewController = storyboard.instantiateViewControllerWithIdentifier(
                EditViewController::class.simpleName!!).uncheckedCast<EditViewController>()

        editViewController.taskItemIndex = didSelectRowAtIndexPath.row.toInt()

        this.navigationController?.pushViewController(editViewController, animated = true)
    }

    override fun tableView(tableView: UITableView, canEditRowAtIndexPath: NSIndexPath): Boolean {
        return true
    }

    override fun tableView(tableView: UITableView, commitEditingStyle: UITableViewCellEditingStyle, forRowAtIndexPath: NSIndexPath) {
        if (commitEditingStyle == UITableViewCellEditingStyle.UITableViewCellEditingStyleDelete) {
            taskList.removeAt(forRowAtIndexPath.row.toInt())
            tableView.deleteRowsAtIndexPaths(listOf(forRowAtIndexPath).toNSArray(),
                    withRowAnimation = UITableViewRowAnimationAutomatic)
        }
    }

    @ObjCAction
    private fun addButtonDidTap(sender: ObjCObject?) {
        val storyboard = UIStoryboard.storyboardWithName(MAIN_STORYBOARD_NAME, bundle = null)
        val editViewController = storyboard.instantiateViewControllerWithIdentifier(
                EditViewController::class.simpleName!!)

        val navigationController = UINavigationController(rootViewController = editViewController)

        this.presentViewController(navigationController, animated = true, completion = null)
    }
}

@ExportObjCClass
class TaskItemTableViewCell(aDecoder: NSCoder) : UITableViewCell(aDecoder) {

    @ObjCOutlet
    private lateinit var taskTitleLabel: UILabel

    @ObjCOutlet
    private lateinit var taskDeadlineLabel: UILabel

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(TaskItemTableViewCell(aDecoder))

    var taskTitle: String?
        get() = this.taskTitleLabel.text
        set(value) {
            this.taskTitleLabel.text = value
        }

    var taskDeadline: NSDate?
        get() = this.taskDeadlineLabel.text?.toNSDate(TASK_DATE_FORMAT)
        set(value) {
            this.taskDeadlineLabel.text = value?.format(TASK_DATE_FORMAT)
        }
}
