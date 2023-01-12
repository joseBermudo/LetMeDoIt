package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.util.*

@Keep
@Parcelize
data class Subcategory(
    val nombre : String = "",
    val description: String = "",
    val id : String = UUID.randomUUID().toString()
):Parcelable
