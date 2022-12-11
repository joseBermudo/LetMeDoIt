package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersMap (
    val userOneId : String = "",
    val userTwoId : String = ""

) : Parcelable