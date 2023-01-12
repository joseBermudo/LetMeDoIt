package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ScheduleMap (
    val initHour : String = "",
    val endHour  : String = ""
) : Parcelable