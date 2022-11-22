package cat.copernic.letmedoit.General.model.data

import com.google.firebase.firestore.Exclude
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

data class Service(
    @get:Exclude val id : String = UUID.randomUUID().toString(),
    val title : String,
    val description : String,
    val category : CategoryMap,
    val image: ArrayList<Image>,
    val n_likes : Int = 0,
    val edited_time : String = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).format(Calendar.getInstance().time),
)
