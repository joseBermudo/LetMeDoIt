package cat.copernic.letmedoit.data.model

import com.google.firebase.firestore.Exclude
import java.util.*
import kotlin.collections.ArrayList


data class Category(

    var nombre: String = "",
    var description: String = "",
    var subcategories: ArrayList<Subcategory> = arrayListOf(Subcategory("","","")),
    val image: String = "",
    val id : String = UUID.randomUUID().toString(),
)
