import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

@ExportObjCClass
class EditViewController(aDecoder: NSCoder) : UIViewController(aDecoder) {

    override fun initWithCoder(aDecoder: NSCoder) = this.initBy(EditViewController(aDecoder))

    override fun debugDescription() = this::class.simpleName!!
}