package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryMap (
    val id_category : String = "",
    val id_subcategory : String = "",
) : Parcelable