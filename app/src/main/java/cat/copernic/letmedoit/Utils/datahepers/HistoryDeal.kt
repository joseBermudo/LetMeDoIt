package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import cat.copernic.letmedoit.Utils.datahepers.UserDeals
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryDeal (
     @get:Exclude val userId : String = "",
    val dealId : ArrayList<UserDeals> = arrayListOf()
) : Parcelable