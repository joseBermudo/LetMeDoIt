package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class UsersMap (
    val userOneId : String = "",
    val userTwoId : String = ""

) : Parcelable