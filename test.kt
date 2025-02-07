import android.graphics.Bitmap 
import com.google.zxing.* 
import com.google.zxing.common.HybridBinarizer 
 
fun decodeQRCodeFromBitmap(bitmap: Bitmap): String? {
    return try {
        // 将Bitmap转换为像素数组 
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
 
        // 创建ZXing可识别的数据源 
        val source = RGBLuminanceSource(bitmap.width, bitmap.height, pixels)
        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
 
        // 配置解码参数 
        val hints = hashMapOf<DecodeHintType, Any>().apply {
            put(DecodeHintType.TRY_HARDER, true)  // 提高识别率 
            put(DecodeHintType.POSSIBLE_FORMATS, listOf(BarcodeFormat.QR_CODE))
        }
 
        // 执行解码 
        MultiFormatReader().apply {
            setHints(hints)
        }.decode(binaryBitmap).text 
    } catch (e: NotFoundException) {
        null  // 未找到二维码 
    } catch (e: ChecksumException) {
        null  // 数据校验失败 
    } catch (e: FormatException) {
        null  // 格式错误 
    } catch (e: Exception) {
        null  // 其他异常 
    }
}
