package cat.copernic.letmedoit

import android.content.Context
import android.widget.Spinner
import android.widget.ArrayAdapter

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
        fun AsignarPopUpSpinner(context: Context,list: ArrayList<String>, spinner: Spinner, layout : Int=R.layout.spinner_items) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(context, R.layout.spinner_items, list)
            spinner.adapter = adapter
        }
    }

}