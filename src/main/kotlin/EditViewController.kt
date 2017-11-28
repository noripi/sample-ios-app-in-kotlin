import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class EditViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    @ObjCOutlet
    private lateinit var taskTitleTextField: UITextField

    @ObjCOutlet
    private lateinit var taskDeadlineLabel: UILabel

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(EditViewController(aDecoder))

    override fun debugDescription() = this::class.simpleName!!

    override fun viewDidLoad() {
        super.viewDidLoad()

        this.title = "タスクを追加"
        this.navigationItem.leftBarButtonItem = UIBarButtonItem(
                title = "閉じる",
                style = UIBarButtonItemStylePlain,
                target = this,
                action = NSSelectorFromString("closeButtonDidTap:")
        )
    }

    @ObjCAction
    fun closeButtonDidTap(sender: ObjCObject?) {
        taskList.add(TaskItem(title = "郵便局に行く", deadline = "2017.11.30 12:00"))

        this.dismissViewControllerAnimated(true, completion = null)
    }
}