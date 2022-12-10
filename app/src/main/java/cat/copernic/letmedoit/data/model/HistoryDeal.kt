package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryDeal (
     @get:Exclude val userId : String = "",
    val dealId : ArrayList<UserDeals> = arrayListOf()
) : Parcelable