package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * Clase que contiene la id de un trato.
 * Utilizada principalmente por User
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param deal_id id del trato
 */
@Keep
@Parcelize
data class UserDeals (
    val deal_id : String = ""
) : Parcelable