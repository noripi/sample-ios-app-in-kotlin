import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class ViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    @ObjCOutlet
    private lateinit var label: UILabel

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(ViewController(aDecoder))

    override fun debugDescription() = "ViewController"

    override fun viewDidLoad() {
        super.viewDidLoad()

        this.label.text = "TEST"
    }
}
