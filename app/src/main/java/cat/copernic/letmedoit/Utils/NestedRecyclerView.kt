package cat.copernic.letmedoit.Utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.view.NestedScrollingParent
import androidx.recyclerview.widget.RecyclerView

/**
 * Recycler View adaptado para introducirse dentro de un scroll.ç
 * Se utiliza principalmente en menus de desplazamiento lateral,
 * como las opiniones de usuarios.
 */
open class NestedRecyclerView : RecyclerView, NestedScrollingParent {

    private var nestedScrollTarget: View? = null
    private var nestedScrollTargetIsBeingDragged = false
    private var nestedScrollTargetWasUnableToScroll = false
    private var skipsTouchInterception = false

    /**
     * Llama al constructor del padre (RecylcerView)
     */
    constructor(context: Context) :
            super(context)

    /**
     * Llama al constructor del padre
     */
    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs)

    /**
     * Llama al constructor del padre
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    /**
     * Funcion que desactivar el deslizamiento vertical del RecyclerView si se esta usando
     * el deslizamiento de lateral
     * @param ev Evento deslizamiento
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val temporarilySkipsInterception = nestedScrollTarget != null
        if (temporarilySkipsInterception) {
            // Si una vista descendente se está desplazando, establecemos una bandera para omitir temporalmente nuestra implementación onInterceptTouchEvent
            skipsTouchInterception = true
        }

        // Primer envío, potencialmente omitiendo nuestro onInterceptTouchEvent
        var handled = super.dispatchTouchEvent(ev)

        if (temporarilySkipsInterception) {
            skipsTouchInterception = false

            // Si el primer envío no dio resultado o notamos que la vista descendente no puede desplazarse en la dirección // en la que el usuario se desplaza, despachamos una vez más pero sin saltar nuestro onInterceptTouchEvent.
            // dirección en la que se desplaza el usuario, ejecutamos una vez más pero sin saltar nuestro onInterceptTouchEvent.
            // Ten en cuenta que RecyclerView cancela automáticamente los toques activos de todos sus descendientes una vez que comienza a desplazarse
            // por lo que no tenemos que hacer eso.
            if (!handled || nestedScrollTargetWasUnableToScroll) {
                handled = super.dispatchTouchEvent(ev)
            }
        }

        return handled
    }


    /**
     * Omite onInterceptTouchEvent de RecyclerView si se solicita
     * @param e evento deslizamiento
     */
    override fun onInterceptTouchEvent(e: MotionEvent) =
        !skipsTouchInterception && super.onInterceptTouchEvent(e)

    /**
     *Selecciona que debe deslizarse, si el recyclerView o el deslizamiento lateral
     * @param target vista
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUncusumed
     * @param dyUnconsumed
     */
    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        if (target === nestedScrollTarget && !nestedScrollTargetIsBeingDragged) {
            if (dyConsumed != 0) {
                // El descendiente fue desplazado, así que no lo molestaremos más.
                // Recibirá todos los eventos futuros hasta que termine de desplazarse.
                nestedScrollTargetIsBeingDragged = true
                nestedScrollTargetWasUnableToScroll = false
            }
            else if (dyConsumed == 0 && dyUnconsumed != 0) {
                // El descendiente intentó desplazarse en respuesta a los movimientos táctiles pero no fue capaz de hacerlo.
                // Recordamos que para permitir que RecyclerView se encargue del desplazamiento.
                nestedScrollTargetWasUnableToScroll = true
                target.parent?.requestDisallowInterceptTouchEvent(false)
            }
        }
    }

    /**
     * Funcion que desliza
     */
    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        if (axes and View.SCROLL_AXIS_VERTICAL != 0) {
            // Un descendiente comenzó a desplazarse, así que lo observaremos.
            nestedScrollTarget = target
            nestedScrollTargetIsBeingDragged = false
            nestedScrollTargetWasUnableToScroll = false
        }

        super.onNestedScrollAccepted(child, target, axes)
    }


    /**
     * Sólo admitimos desplazamiento vertical.
     */
    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int) =
        (nestedScrollAxes and View.SCROLL_AXIS_VERTICAL != 0)

    /**
     *El descendiente terminó de desplazarse. ¡Límpialo!
     */
    override fun onStopNestedScroll(child: View) {
        nestedScrollTarget = null
        nestedScrollTargetIsBeingDragged = false
        nestedScrollTargetWasUnableToScroll = false
    }
}