package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
/**
 * FavoritesMap que contiene los servicios y perfiles favoritos de un usuario.
 * Contiene dos listas, una de servicios y otra de perfiles
 * Utilizada principalmente por User
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param profilesId lista de usuarios
 * @param servicesId lista de servicios
 */
@Keep
@Parcelize
data class FavoritesMap (
    val profilesId : ArrayList<String>,
    val servicesId : ArrayList<String>
) : Parcelable