import android.graphics.Bitmap 
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer 
 
fun Bitmap.decodeQRCode(): String? {
    // 将Bitmap转换为LuminanceSource 
    val source = RGBLuminanceSource(this)
    // 创建二进制位图 
    val binarizer = HybridBinarizer(source)
    val binaryBitmap = BinaryBitmap(binarizer)
    
    // 创建解码器 
    val reader = MultiFormatReader()
    // 设置解码参数 
    val hints = EnumMap<DecodeHintType, Any>(DecodeHintType::class.java)
    hints[DecodeHintType.POSSIBLE_FORMATS] = BarcodeFormat.QR_CODE 
    
    try {
        // 解码二维码 
        val result = reader.decode(binaryBitmap, hints)
        return result.text 
    } catch (e: Exception) {
        e.printStackTrace()
        return null 
    }
}
