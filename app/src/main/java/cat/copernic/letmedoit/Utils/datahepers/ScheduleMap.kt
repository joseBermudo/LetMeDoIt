package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
/**
 * SheduleMao que contiene la hora inicial y hora final.
 * Utilizada por el User
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param initHour hora de inicio
 * @param endHour hora final
 */
@Keep
@Parcelize
data class ScheduleMap (
    val initHour : String = "",
    val endHour  : String = ""
) : Parcelable