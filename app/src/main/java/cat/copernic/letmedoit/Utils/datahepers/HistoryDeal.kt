package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import cat.copernic.letmedoit.Utils.datahepers.UserDeals
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class HistoryDeal (
     @get:Exclude val userId : String = "",
    val dealId : ArrayList<UserDeals> = arrayListOf()
) : Parcelable