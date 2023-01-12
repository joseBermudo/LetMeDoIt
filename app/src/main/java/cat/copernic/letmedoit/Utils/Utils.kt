package cat.copernic.letmedoit.Utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.presentation.view.general.fragments.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.sqrt


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
         * Función que muestra un AlertDialog con el titulo especificado y un botón de OK
         * @         * */
        fun showOkDialog(title : String,context: Context, message : String = "", activity: Activity) {
            val alertDialog: AlertDialog = context.let {
                val builder = MaterialAlertDialogBuilder(context,R.style.Widget_LetMeDoIt_AlertDialogTheme)
                builder.apply {
                    this.setTitle(title)
                    this.setMessage(message)

                    setPositiveButton(R.string.ok) { dialog, id ->
                        // User clicked OK button
                    }
                }
                // Create the AlertDialog
                builder.create()
            }
            val isTablet = checkIfTablet(activity)
            alertDialog.show()
            if(isTablet){
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).textSize = context.resources.getDimension(R.dimen.tablet_textInput_text)
                alertDialog.findViewById<TextView>(androidx.appcompat.R.id.alertTitle)?.textSize = context.resources.getDimension(R.dimen.tablet_textInput_text)
                alertDialog.findViewById<TextView>(android.R.id.message)?.textSize = context.resources.getDimension(R.dimen.tablet_textInput_text)
            }


        }

        private fun checkIfTablet(activity: Activity): Boolean {
            val metrics = DisplayMetrics()
            //Esto es compatible con API 21, la función que no está deprecated no (es API 30)
             activity.windowManager.defaultDisplay.getMetrics(metrics)

            val yInches = metrics.heightPixels / metrics.ydpi
            val xInches = metrics.widthPixels / metrics.xdpi
            val diagonalInches = sqrt((xInches * xInches + yInches * yInches).toDouble())
            return diagonalInches >= 9
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

        fun goToUserReport(view: View, id: String) {
            val destinationLabel = Navigation.findNavController(view).currentDestination?.label

            val action = when(destinationLabel){
                "fragment_view_service" -> viewServiceDirections.actionViewServiceToUserReport(id)
                else -> PerfilUsuarioMenuSuperiorDirections.actionPerfilUsuarioMenuSuperiorToUserReport(id)
            }

            Navigation.findNavController(view).navigate(action)
        }


    }

}