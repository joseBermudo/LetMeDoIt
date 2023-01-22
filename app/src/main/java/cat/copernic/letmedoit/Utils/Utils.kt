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
import cat.copernic.letmedoit.R.layout
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.presentation.view.general.fragments.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.sqrt

/**
 * Clase utils contiene funciones que usamos repetidamente en diferentes partes del codigo
 */
abstract class Utils {
    //Companion Object --> permite llamar a la función sin instanciar la clase


    companion object {
        /**
         * Función que asigna el PopUp al spinner.
         *  PopUp: Cuadro que aparece al clicar sobre el desplegable del spinner.
         *  @param context Parametro que indica el contexto donde se encuentra el spinner. (Normalmente this)
         *  @param list Lista de valor que adopta el PopUp del spinner.
         *  @param spinner El spinner al cual se asigna el PopUp.
         *  @param layout El archivo xml que será el diseño del PopUp
         */
        fun AsignarPopUpSpinner(
            context: Context,
            list: ArrayList<String>, spinner: Spinner, layout: Int = R.layout.spinner_items
        ) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                context,
                R.layout.spinner_items, list
            )
            spinner.adapter = adapter
        }

        /**
         * Función que muestra un AlertDialog con el titulo especificado y un botón de OK
         * @param title Titulo que mostrara el AlertDialog
         * @param context El contexto donde sea abrira el AlertDialog
         * @param message El mensage que mostrara el AlertDialog
         * @param activity Activity donde abre el AlertDialog
         */
        fun showOkDialog(
            title: String,
            context: Context,
            message: String = "",
            activity: Activity
        ) {
            val alertDialog: AlertDialog = context.let {
                val builder =
                    MaterialAlertDialogBuilder(context, R.style.Widget_LetMeDoIt_AlertDialogTheme)
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
            if (isTablet) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).textSize =
                    context.resources.getDimension(R.dimen.tablet_textInput_text)
                alertDialog.findViewById<TextView>(androidx.appcompat.R.id.alertTitle)?.textSize =
                    context.resources.getDimension(R.dimen.tablet_textInput_text)
                alertDialog.findViewById<TextView>(android.R.id.message)?.textSize =
                    context.resources.getDimension(R.dimen.tablet_textInput_text)
            }


        }

        /**
         * Funcion: Comprueva si el dispositivo es una tablet
         * @param activity Activity actual
         * @return true si es una tabler y false si no
         */
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
         * Función para Navegagar entre fragments.
         * @param view la vista actual
         * @param destination Fragment al que vamos
         */
        fun goToDestination(view: View, destination: Int) {
            Navigation.findNavController(view).navigate(destination)
        }

        /**
         * Función que nos dirige a al fragment de reportar usuario
         * @param view vista actual
         * @param id referencia al fragment de reportar usuario
         */
        fun goToUserReport(view: View, id: String) {
            val destinationLabel = Navigation.findNavController(view).currentDestination?.label

            val action = when (destinationLabel) {
                "fragment_view_service" -> viewServiceDirections.actionViewServiceToUserReport(id)
                else -> PerfilUsuarioMenuSuperiorDirections.actionPerfilUsuarioMenuSuperiorToUserReport(
                    id
                )
            }

            Navigation.findNavController(view).navigate(action)
        }

        fun getCategoryIcons(): ArrayList<String> {
            return arrayListOf(
                "ic_baseline_computer_24",
                "ic_baseline_sports_basketball_24",
                "ic_baseline_cast_for_education_24",
                "ic_baseline_construction_24",
                "ic_baseline_draw_24",
                "ic_baseline_music_note_24",
                "ic_baseline_local_bar_24",
                "ic_baseline_business_center_24",
                "ic_baseline_album_24",
                "ic_baseline_agriculture_24",
                "ic_baseline_snowmobile_24",
                "ic_baseline_pets_24",
                "ic_baseline_card_travel_24",
                "ic_baseline_directions_car_24",
                "ic_baseline_dining_24",
                "ic_baseline_desktop_windows_24",
                "ic_baseline_description_24",
                "ic_baseline_deck_24",
                "ic_baseline_cruelty_free_24",
                "ic_baseline_admin_panel_settings_24",
                "ic_baseline_add_alert_24",
                "ic_baseline_accessible_forward_24",
                "ic_baseline_accessibility_24",
                "ic_baseline_ac_unit_24",
                "ic_baseline_anchor_24",
                "ic_baseline_airplanemode_active_24",
                "ic_baseline_monetization_on_24",
                "ic_baseline_mic_24",
                "ic_baseline_camera_alt_24",
                "ic_baseline_videogame_asset_24",
                "ic_baseline_health_and_safety_24"
            )
        }


    }

}