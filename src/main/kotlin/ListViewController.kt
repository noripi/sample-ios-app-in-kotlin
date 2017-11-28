import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class ListViewController(aDecoder: NSCoder) : UIViewController(aDecoder),
                                              UITableViewDelegateProtocol,
                                              UITableViewDataSourceProtocol {

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(ListViewController(aDecoder))

    override fun debugDescription() = this::class.simpleName!!

    override fun tableView(tableView: UITableView, numberOfRowsInSection: NSInteger): NSInteger {
        return 10
    }

    override fun tableView(tableView: UITableView, cellForRowAtIndexPath: NSIndexPath): UITableViewCell {
        val cell = tableView.dequeueReusableCellWithIdentifier(TaskItemTableViewCell::class.simpleName!!,
                cellForRowAtIndexPath)

        return cell
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

    var taskDeadline: String?
        get() = this.taskDeadlineLabel.text
        set(value) {
            this.taskDeadlineLabel.text = value
        }
}
