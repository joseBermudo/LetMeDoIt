package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryDeal (
    val userId : String,
    val dealId : String
) : Parcelable