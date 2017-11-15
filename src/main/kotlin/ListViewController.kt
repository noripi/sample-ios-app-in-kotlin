import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class ListViewController(aDecoder: NSCoder) : UIViewController(aDecoder),
                                              UITableViewDelegateProtocol,
                                              UITableViewDataSourceProtocol {
    @ObjCOutlet
    private lateinit var label: UILabel

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(ListViewController(aDecoder))

    override fun debugDescription() = "ListViewController"

    override fun viewDidLoad() {
        super.viewDidLoad()

        this.title = "タスク一覧"
    }

    override fun tableView(tableView: UITableView, numberOfRowsInSection: Long): Long {
        return 10
    }

    override fun tableView(tableView: UITableView, cellForRowAtIndexPath: NSIndexPath): UITableViewCell {
        val cell = tableView.dequeueReusableCellWithIdentifier("TaskItemCell",
                cellForRowAtIndexPath)

        return cell
    }
}
