package cat.copernic.letmedoit.General.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cat.copernic.letmedoit.Admin.model.Service
import cat.copernic.letmedoit.Admin.model.ServiceProvider

class SearchViewViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    fun sendQuery(query : String){
        message.value = query
    }
}