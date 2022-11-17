package cat.copernic.letmedoit.General.model.data

import com.google.firebase.firestore.Exclude
import java.util.Date

data class Service(
    @get:Exclude val id : String,
    val title : String,
    val description : String,
    val category : CategoryMap,
    val image: ArrayList<Image>,
    val n_likes : Int,
    val edited_time : String,
)
