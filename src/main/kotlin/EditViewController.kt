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
        this.navigationItem.rightBarButtonItem = UIBarButtonItem(
                title = "追加",
                style = UIBarButtonItemStylePlain,
                target = this,
                action = NSSelectorFromString("submitButtonDidTap:")
        )

        this.taskDeadlineLabel.text = "未設定"
    }

    @ObjCAction
    fun closeButtonDidTap(sender: ObjCObject?) {
        val alert = UIAlertController.alertControllerWithTitle(
                title = "確認",
                message = "編集中の画面を閉じてもよろしいですか？",
                preferredStyle = UIAlertControllerStyleAlert
        )
        alert.addAction(UIAlertAction.actionWithTitle(
                title = "Cancel",
                style = UIAlertActionStyleCancel,
                handler = null
        ))
        alert.addAction(UIAlertAction.actionWithTitle(
                title = "閉じる",
                style = UIAlertActionStyleDefault,
                handler = {
                    this.dismissViewControllerAnimated(true, completion = null)
                }
        ))
        this.presentViewController(alert, animated = true, completion = null)
    }

    @ObjCAction
    fun submitButtonDidTap(sender: ObjCObject?) {
        taskList.add(TaskItem(
                title = this.taskTitleTextField.text!!,
                deadline = "2017.11.30 12:00"
        ))
        this.dismissViewControllerAnimated(true, completion = null)
    }
}