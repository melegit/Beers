package mobi.mele.beers.extensions

import android.util.Log
import mobi.mele.beers.BuildConfig

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
fun Any?.log(tag : String = "mobi.mele.DEBUG"){
    if(BuildConfig.DEBUG){
        Log.d(tag,this.toString())
    }
}