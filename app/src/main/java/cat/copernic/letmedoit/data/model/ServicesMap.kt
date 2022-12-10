package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServicesMap (
    val serviceOneId : String = "",
    val serviceTwoId : String = ""
) : Parcelable