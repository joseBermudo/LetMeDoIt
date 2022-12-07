package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import cat.copernic.letmedoit.Utils.Constants
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class Service(
    @get:Exclude var id : String = UUID.randomUUID().toString(),
    val title : String = "",
    val description : String = "",
    val category : CategoryMap = CategoryMap("",""),
    @get:Exclude val image: ArrayList<Image> = arrayListOf(Image("","")),
    var n_likes : Int = 0,
    val edited_time : String = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).format(Calendar.getInstance().time),
    val userid : String = Constants.USER_LOGGED_IN_ID,
    @get:Exclude var defaultFav: Boolean = false
) : Parcelable
