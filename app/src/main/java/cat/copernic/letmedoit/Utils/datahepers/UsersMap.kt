package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * UserMap que contiene el id de dos usuarios
 * Utilizada principalmente por la clase Reports y Deal.
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param userOneId id del usuario 1
 * @param userTwoId id del usario 2
 */
@Keep
@Parcelize
data class UsersMap (
    val userOneId : String = "",
    val userTwoId : String = ""

) : Parcelable