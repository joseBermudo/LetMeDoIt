package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class Category(

    var nombre: String = "",
    var description: String = "",
    var subcategories: ArrayList<Subcategory> = arrayListOf(Subcategory("","","")),
    val image: String = "",
    val id : String = UUID.randomUUID().toString(),
) : Parcelable
