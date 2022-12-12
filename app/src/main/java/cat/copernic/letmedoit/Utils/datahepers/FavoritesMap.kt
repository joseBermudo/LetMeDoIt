package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoritesMap (
    val profilesId : ArrayList<String>,
    val servicesId : ArrayList<String>
) : Parcelable