package cat.copernic.letmedoit.data.model

import com.google.firebase.firestore.Exclude
import java.util.*
import kotlin.collections.ArrayList


data class Category(

    val nombre: String = "",
    val description: String = "",
    var subcategories: ArrayList<Subcategory> = arrayListOf(Subcategory("","","")),
    val image: String = "",
    @get:Exclude val id : String = UUID.randomUUID().toString()
)
