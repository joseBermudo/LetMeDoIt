package cat.copernic.letmedoit.General.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    fun sendQuery(query : String){
        message.value = query
    }
}