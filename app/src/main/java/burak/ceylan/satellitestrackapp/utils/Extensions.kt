package burak.ceylan.satellitestrackapp.utils

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

const val SAT_ID = "sat_id"
const val SAT_NAME = "sat_name"
const val COST_PER_LAUNCH = "cost_per_launch"
const val FIRST_FLIGHT = "first_flight"
const val HEIGHT = "height"
const val MASS = "mass"

fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun showLog(mssg: Any) {
    Log.e("logs->", "$mssg")
}

fun EditText.onDone(action:Int, runAction:()->Unit){
    this.setOnEditorActionListener { v, actionId, event ->
        return@setOnEditorActionListener when(actionId){
            action ->{
                runAction.invoke()
                true
            }
            else->false
        }
    }


}
fun getJsonFromAssets(context: Context, fileName: String): String {
    return try {
        val `is`: InputStream = context.assets.open(fileName)
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer, Charset.forName("UTF-8"))
    } catch (e: IOException) {
        e.printStackTrace()
        return ""
    }
}