package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Opinions (

    val id :            String,
    val user :          String,
    val service :       String,
    val rating  :       Float,
    val description:    String

) : Parcelable