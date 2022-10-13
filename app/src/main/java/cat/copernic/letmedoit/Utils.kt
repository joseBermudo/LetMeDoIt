package cat.copernic.letmedoit

import android.content.Context
import android.widget.Spinner
import android.widget.ArrayAdapter
import cat.copernic.letmedoit.Users.CreateDeal

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
            list: ArrayList<String>, spinner: Spinner, layout: Int=R.layout.spinner_items) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(context, R.layout.spinner_items, list)
            spinner.adapter = adapter
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