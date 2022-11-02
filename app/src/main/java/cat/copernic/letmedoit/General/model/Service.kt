package cat.copernic.letmedoit.General.model

import java.util.Date

data class Service(
    val id : String,
    val title : String,
    val description : String,
    val category : cat.copernic.letmedoit.General.model.CategoryMap,
    val image: ArrayList<cat.copernic.letmedoit.General.model.Image>,
    val n_likes : Int,
    val edited_time : Date,
)
