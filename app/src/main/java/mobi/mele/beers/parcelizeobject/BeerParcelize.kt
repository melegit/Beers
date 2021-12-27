package mobi.mele.beers.parcelizeobject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/**
 * Created by Antonio Fernández
 * date   : 27/12/21
 * e-mail : meleappdev@gmail.com
 */
@Parcelize
class BeerParcelize(
    val id: Int,
    val name: String,
    val abv: Double,
    val description: String,
    val image_url: String
) : Parcelable