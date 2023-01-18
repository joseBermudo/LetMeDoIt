package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/**
 * Clase que contiene la id de un chat.
 * Utilizada principalmente por User
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * pd: Esta clase para futuras versiones con chat
 * @param chats_id id del trato
 */
@Parcelize
data class UserChats (
    val chats_id : String = ""
) : Parcelable