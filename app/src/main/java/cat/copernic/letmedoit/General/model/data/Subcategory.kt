package cat.copernic.letmedoit.General.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subcategory(
    val nombre : String,
    val description: String,
    val id : String
):Parcelable
