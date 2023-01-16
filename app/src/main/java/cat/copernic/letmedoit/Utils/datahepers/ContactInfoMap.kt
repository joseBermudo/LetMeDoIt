package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
/**
 * ContactInfoMap contiene el email y telefono de un usuario
 * Utilizada princpalmente por User
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param email correo electronico
 * @param servicesId telefono
 */
@Keep
@Parcelize
data class ContactInfoMap (
        val email : String = "",
        val phone : String = ""
) : Parcelable