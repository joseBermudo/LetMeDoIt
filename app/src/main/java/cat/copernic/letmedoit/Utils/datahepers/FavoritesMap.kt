package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class FavoritesMap (
    val profilesId : ArrayList<String>,
    val servicesId : ArrayList<String>
) : Parcelable