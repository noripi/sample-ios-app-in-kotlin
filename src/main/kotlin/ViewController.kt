import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class ViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {
    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(ViewController(aDecoder))

    override fun debugDescription() = "ViewController"
}
