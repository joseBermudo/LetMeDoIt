package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDeals (
    val deal_id : String = ""
) : Parcelable