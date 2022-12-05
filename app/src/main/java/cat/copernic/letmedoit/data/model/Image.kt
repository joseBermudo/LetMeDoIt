package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image (
        @get:Exclude val  id : String = "",
        val img_link : String = "",
        @get:Exclude var checked : Boolean? = false
) : Parcelable