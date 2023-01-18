package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import cat.copernic.letmedoit.Utils.datahepers.UserDeals
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

/**
 * Clase que contiene un lista de tratos correspondeinte a un usuario
 * Las instancias de esta clase pueden pasarse por parametro (SafeArg)
 * @param userId la id del usuario, no se tiene encuenta en la base de datos
 * @param dealId lisda de tratos
 */
@Keep
@Parcelize
data class HistoryDeal (
     @get:Exclude val userId : String = "",
    val dealId : ArrayList<UserDeals> = arrayListOf()
) : Parcelable