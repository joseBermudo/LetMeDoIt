package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * Clase uqe contiene la id de un servicio
 * Utilizada principalmente por User.
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param service_id id del servicio
 */
@Keep
@Parcelize
data class UserServices (
    val service_id : String = ""
) : Parcelable