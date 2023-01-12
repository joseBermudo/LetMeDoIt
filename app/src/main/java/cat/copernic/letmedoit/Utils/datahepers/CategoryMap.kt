package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class CategoryMap (
    val id_category : String = "",
    val id_subcategory : String = "",
) : Parcelable