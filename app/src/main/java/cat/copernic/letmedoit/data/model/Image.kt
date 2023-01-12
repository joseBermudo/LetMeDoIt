package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Image (
    @get:Exclude var id : String = "",
    val img_link : String = "",
    val index : Int = 0,
    @get:Exclude var checked : Boolean? = false
) : Parcelable