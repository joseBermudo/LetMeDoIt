package cat.copernic.letmedoit.presentation.adapter.general

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Utils.UserConstants
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ServiceTemplateBinding
import cat.copernic.letmedoit.presentation.adapter.general.viewholder.ServiceViewHolder
import cat.copernic.letmedoit.presentation.view.general.fragments.HomeServicesList
import cat.copernic.letmedoit.presentation.view.users.fragments.VerListadoFavServices
import cat.copernic.letmedoit.presentation.view.users.fragments.viewFavUsers
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.checkerframework.checker.units.qual.s
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Adaptador de Categorias, implementa Filterable para así poder filtrar :)
 * @param categoryList ArrayList de servicios
 * */
class ServiceAdapter(private var serviceList:ArrayList<Service>,val fragment: Fragment? = null, private val userViewModel: UserViewModel,private val serviceViewModel: ServiceViewModel) : RecyclerView.Adapter<ServiceViewHolder>() {

    lateinit var destinationLabel : String
    var favFragment = false
    /**
     * Función ejecutada al crear el View Holder. infla el XML del item de categorias.
     * @param parent Padre
     * @param viewType Tipo de vista
     * @return Devuelve un CategoryViewHolder cargando la vista con los datos del listado.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {

        val binding = ServiceTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)

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

    fun sortByCategory(category: Category?) {
        if (category != null) {
            serviceListFiltered = serviceListFiltered.filter { it.category.id_category == category.nombre } as ArrayList<Service>
            notifyDataSetChanged()
        }
    }
    private fun sortByCategorySubcategory(category: Category?){
        if (category != null) {
            serviceListFiltered =  serviceListFiltered.filter { it.category.id_category == category.nombre && it.category.id_subcategory == category.subcategories[0].nombre } as ArrayList<Service>
        }
    }
    fun orderByName(category: Category?){
        serviceListFiltered.sortBy { it.title }
        sortByCategorySubcategory(category)
        notifyDataSetChanged()
    }
    fun orderByNewestDate(category: Category?){
        val dateFormat = SimpleDateFormat("dd-MM-yyyy",Locale.US)
        serviceListFiltered.sortWith(
            compareByDescending { dateFormat.parse(it.edited_time)?.time }
        )
        sortByCategorySubcategory(category)
        notifyDataSetChanged()
    }
    fun orderByOldestDate(category: Category?){
        val dateFormat = SimpleDateFormat("dd-MM-yyyy",Locale.US)
        serviceListFiltered.sortWith(
            compareBy { dateFormat.parse(it.edited_time)?.time }
        )
        sortByCategorySubcategory(category)
        notifyDataSetChanged()
        val a = ArrayList<String>()
    }

    fun clearFilters(){
        serviceListFiltered = serviceList
        notifyDataSetChanged()
    }
    fun clear(){
        var size = serviceListFiltered.size
        serviceListFiltered.clear()
        notifyItemRangeRemoved(0,size)

        size = serviceList.size
        serviceList.clear()
        notifyItemRangeRemoved(0,size)
    }

    fun deleteFavService(service: Service){
        when(fragment){
            is VerListadoFavServices ->  {
                val position = serviceListFiltered.indexOf(service)
                serviceListFiltered.remove(service)
                notifyItemRemoved(position)
            }
        }
        UserConstants.USER_FAVORITE_SERVICES_IDS.remove(service.id)
        userViewModel.deleteFavoriteService(service.id)
        serviceViewModel.updateNLikes(service.id, --service.n_likes)
    }

    fun addFavService(service: Service) {
        userViewModel.addFavoriteService(service.id)
        serviceViewModel.updateNLikes(service.id, ++service.n_likes)
    }

    fun removeService(service: Service) {
        val position = serviceListFiltered.indexOf(service)
        serviceListFiltered.remove(service)
        notifyItemRemoved(position)
    }


}