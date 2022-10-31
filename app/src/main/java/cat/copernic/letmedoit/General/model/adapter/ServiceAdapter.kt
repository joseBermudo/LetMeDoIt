package cat.copernic.letmedoit.General.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Service
import cat.copernic.letmedoit.databinding.ServiceTemplateBinding
import java.util.*
import kotlin.collections.ArrayList

/**
 * Adaptador de Categorias, implementa Filterable para así poder filtrar :)
 * @param categoryList ArrayList de servicios
 * */
class ServiceAdapter(private var serviceList:ArrayList<Service>) : RecyclerView.Adapter<ServiceViewHolder>() {

    /**
     * Función ejecutada al crear el View Holder. infla el XML del item de categorias.
     * @param parent Padre
     * @param viewType Tipo de vista
     * @return Devuelve un CategoryViewHolder cargando la vista con los datos del listado.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ServiceTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding,false)
    }

    /**
     * Función que renderiza el item en cuestión desde la función del ViewHolder.
     * */
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val item = serviceListFiltered[position]
        holder.render(item)
    }

    /**
     * @return Devuelve el tamaño del listado.
     * */
    override fun getItemCount(): Int {
        return serviceListFiltered.size
    }

    private var serviceListFiltered : ArrayList<Service> = ArrayList()

    //https://www.androhub.com/android-adding-search-functionality-list/
    init {
        serviceListFiltered.addAll(serviceList)
    }
    fun filter(text: String) {
        var filteredList = ArrayList<Service>()
        if (text.isEmpty()) {
            filteredList.addAll(serviceList)
        } else {
            for (item in serviceList) {
                if (item.title.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                    filteredList.add(item)
                }
            }
        }

        serviceListFiltered.clear()
        serviceListFiltered.addAll(filteredList)
        notifyDataSetChanged()
    }
}