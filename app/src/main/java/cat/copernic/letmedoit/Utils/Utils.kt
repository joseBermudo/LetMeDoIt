package cat.copernic.letmedoit.Utils

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Patterns
import android.view.View
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import cat.copernic.letmedoit.General.model.adapter.CategoryAdapter
import cat.copernic.letmedoit.General.model.adapter.CategoryViewHolder
import cat.copernic.letmedoit.General.model.adapter.SERVICE_ID
import cat.copernic.letmedoit.LISTASDEPRUEBA
import cat.copernic.letmedoit.R
import java.util.Objects
import java.util.regex.Pattern

abstract class Utils {
    //Companion Object --> permite llamar a la función sin instanciar la clase

    /**
     * Función que asigna el PopUp al spinner.
     *  PopUp: Cuadro que aparece al clicar sobre el desplegable del spinner.
     *  @param context Parametro que indica el contexto donde se encuentra el spinner. (Normalmente this)
     *  @param list Lista de valor que adopta el PopUp del spinner.
     *  @param spinner El spinner al cual se asigna el PopUp.
     *  @param layout El archivo xml que será el diseño del PopUp
     */
    companion object{
        fun AsignarPopUpSpinner(
            context: Context,
            list: ArrayList<String>, spinner: Spinner, layout: Int= R.layout.spinner_items
        ) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(context,
                R.layout.spinner_items, list)
            spinner.adapter = adapter
        }

        /**
         * Función que añade un fragmento a un Frame Layout.
         * @param fragment Fragmento (Clase) la cual queremos añadir.
         * @param frameId ID del Fragmento (R.id.NOMBRE)
         * */
        fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag : String){
            supportFragmentManager.inTransaction { add(frameId, fragment,tag) }
        }
        /**
         * Función que reemplaza un fragmento de un Frame Layout.
         * @param fragment Fragmento (Clase) la cual queremos añadir.
         * @param frameId ID del Fragmento (R.id.NOMBRE)
         * */
        fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String) {
            supportFragmentManager.inTransaction{replace(frameId, fragment,tag)}
        }

        /**
         * Función que se ejecuta al añadir o reemplazar un Frame Layout (Hace un commit de la transacción)
         * @param func Función
         * */
        inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
            beginTransaction().func().commit()
        }
        /**
         * Función para Navegagar.
         */
        fun goToDestination(view: View, destination : Int) {
            Navigation.findNavController(view).navigate(destination)
        }
        
        fun createList():ArrayList<String>{
            var itemList: ArrayList<String> = ArrayList<String>()
            itemList.add("Clases de matematicas")
            itemList.add("Desarrollo de apps web")
            itemList.add("Limpieza del hogar")
            itemList.add("Cocina italiana")
            itemList.add("Transporte de mudanza")
            itemList.add("Mantenimiento de piscinas")
            itemList.add("Consultas psicologicas")
            itemList.add("Soporte tecnico sobre ordenadores")
            itemList.add("Diseño de apps (Android)")
            itemList.add("Personalización de teclados")
            itemList.add("Cortes de cabello")
            itemList.add("Manicura")
            itemList.add("Puñetazos")
            itemList.add("Clases de KungFu")
            itemList.add("Clases de cocina")
            itemList.add("Colocación de azulejos")
            itemList.add("Limpiar gafas")
            itemList.add("Masajes")
            itemList.add("Reparación vidrio")
            itemList.add("Destrucion de planetas")
            itemList.add("Doblaje")
            itemList.add("Retratos")
            itemList.add("Edición de memorias")
            itemList.add("Instalación de software de oficina")
            itemList.add("Reparación de ordenadores")
            itemList.add("Panaderia")
            return itemList

        }


    }

}