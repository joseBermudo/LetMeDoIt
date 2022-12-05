package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduleMap (
    val initHour : String = "",
    val endHour  : String = ""
) : Parcelable