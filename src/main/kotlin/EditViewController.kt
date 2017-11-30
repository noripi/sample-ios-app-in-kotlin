import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*
import extensions.*

@ExportObjCClass
class EditViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    @ObjCOutlet
    private lateinit var taskTitleTextField: UITextField

    @ObjCOutlet
    private lateinit var taskDeadlineLabel: UILabel

    @ObjCOutlet
    private lateinit var datePicker: UIDatePicker

    @ObjCOutlet
    private lateinit var datePickerViewBottomOffset: NSLayoutConstraint

    // Injectable Parameter
    var taskItemIndex: Int? = null

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(EditViewController(aDecoder))

    override fun debugDescription() = this::class.simpleName!!

    override fun viewDidLoad() {
        super.viewDidLoad()

        val taskItemIndex = this.taskItemIndex
        if (taskItemIndex == null) {
            // case of new addition
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
        } else {
            // case of edit
            this.title = "タスクを編集"
            this.navigationItem.leftBarButtonItem = UIBarButtonItem(
                    title = "←",
                    style = UIBarButtonItemStylePlain,
                    target = this,
                    action = NSSelectorFromString("closeButtonDidTap:")
            )
            this.navigationItem.rightBarButtonItem = UIBarButtonItem(
                    title = "編集",
                    style = UIBarButtonItemStylePlain,
                    target = this,
                    action = NSSelectorFromString("submitButtonDidTap:")
            )

            this.taskTitleTextField.text = taskList[taskItemIndex].title
            this.taskDeadlineLabel.text = taskList[taskItemIndex].deadline.format(TASK_DATE_FORMAT)
        }
    }

    @ObjCAction
    private fun closeButtonDidTap(sender: ObjCObject?) {
        val alert = UIAlertController.alertControllerWithTitle(
                title = "確認",
                message = "編集中の画面を閉じてもよろしいですか？",
                preferredStyle = UIAlertControllerStyleAlert
        )
        alert.addAction(UIAlertAction.actionWithTitle(
                title = "キャンセル",
                style = UIAlertActionStyleCancel,
                handler = null
        ))
        alert.addAction(UIAlertAction.actionWithTitle(
                title = "閉じる",
                style = UIAlertActionStyleDefault,
                handler = {
                    this.dismissOrPopViewController()
                }
        ))
        this.presentViewController(alert, animated = true, completion = null)
    }

    @ObjCAction
    private fun submitButtonDidTap(sender: ObjCObject?) {
        val title = this.taskTitleTextField.text?.takeIf { it.isNotBlank() } ?: run {
            this.showInsufficientDataAlert()
            return
        }
        val deadline = this.taskDeadlineLabel.text?.toNSDate(TASK_DATE_FORMAT) ?: run {
            this.showInsufficientDataAlert()
            return
        }

        val taskItemIndex = this.taskItemIndex
        if (taskItemIndex == null) {
            taskList.add(TaskItem(title = title, deadline = deadline))
        } else {
            taskList.set(taskItemIndex, TaskItem(title = title, deadline = deadline))
        }
        this.dismissOrPopViewController()
    }

    private fun showInsufficientDataAlert() {
        val alert = UIAlertController.alertControllerWithTitle(
                title = "情報",
                message = "タイトルか締切日が入力されていません",
                preferredStyle = UIAlertControllerStyleAlert
        )
        alert.addAction(UIAlertAction.actionWithTitle(
                title = "OK",
                style = UIAlertActionStyleDefault,
                handler = null
        ))
        this.presentViewController(alert, animated = true, completion = null)
    }

    @ObjCAction
    private fun deadlineUpdateButtonDidTap(sender: ObjCObject?) {
        this.taskTitleTextField.resignFirstResponder()
        this.slideInDatePicker()
    }

    private fun dismissOrPopViewController() {
        if (this.taskItemIndex == null) {
            this.dismissViewControllerAnimated(true, completion = null)
        } else {
            this.navigationController?.popViewControllerAnimated(true)
        }
    }

    /*************************************************************
     * implements Date Picker + Toolbars behaviors
     *************************************************************/
    @ObjCAction
    private fun dateDidCancelChange(sender: ObjCObject?) {
        this.slideOutDatePicker()
    }

    @ObjCAction
    private fun dateDidSubmitChange(sender: ObjCObject?) {
        this.taskDeadlineLabel.text = this.datePicker.date.format(TASK_DATE_FORMAT)
        this.slideOutDatePicker()
    }

    private fun slideInDatePicker() {
        this.datePicker.date = this.taskDeadlineLabel.text?.toNSDate(TASK_DATE_FORMAT) ?: NSDate()
        this.datePickerViewBottomOffset.constant = 0.0

        UIView.animateWithDuration(0.3) {
            this.view.layoutIfNeeded()
        }
    }

    private fun slideOutDatePicker() {
        this.datePickerViewBottomOffset.constant = -260.0

        UIView.animateWithDuration(0.3) {
            this.view.layoutIfNeeded()
        }
    }
}
