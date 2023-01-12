package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ContactInfoMap (
        val email : String = "",
        val phone : String = ""
) : Parcelable