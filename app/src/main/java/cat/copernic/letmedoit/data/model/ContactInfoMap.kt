package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactInfoMap (
        val email : String,
        val phone : String
) : Parcelable