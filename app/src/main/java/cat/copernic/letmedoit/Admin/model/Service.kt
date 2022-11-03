package cat.copernic.letmedoit.Admin.model

import java.util.Date

data class Service(
    val id : String,
    val title : String,
    val description : String,
    val category : cat.copernic.letmedoit.Admin.model.CategoryMap,
    val image: ArrayList<cat.copernic.letmedoit.Admin.model.Image>,
    val n_likes : Int,
    val edited_time : Date,
)
