package cat.copernic.letmedoit.Utils.datahepers

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
/**
 * CategoryMap contiene la categoria y subcategoria de un servicio
 * Utilizada principalmente por Service
 * Sus instancias pueden ser pasadas por parametro (SafeArg)
 * @param id_category id de la categoria
 * @param id_subcategory id de la subcategoria
 */
@Keep
@Parcelize
data class CategoryMap (
    val id_category : String = "",
    val id_subcategory : String = "",
) : Parcelable