package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
/**
 * ServiceMap que contiene la id de dos servicios.
 * Utilizada principalmente por Deal
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param serviceOneId id del servicio 1
 * @param serviceTwoId id del servicio 2
 */
@Keep
@Parcelize
data class ServicesMap (
    val serviceOneId : String = "",
    val serviceTwoId : String = ""
) : Parcelable