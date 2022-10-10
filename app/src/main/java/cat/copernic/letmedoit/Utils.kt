package cat.copernic.letmedoit

import android.content.Context
import android.widget.Spinner
import android.widget.ArrayAdapter

abstract class Utils {
    //Companion Object --> permite llamar a la función sin instanciar la clase
    companion object{
        fun AsignarPopUpSpinner(context: Context,list: ArrayList<String>, spinner: Spinner) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(context, R.layout.spinner_items, list)
            spinner.adapter = adapter
        }
    }

}