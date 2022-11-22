package cat.copernic.letmedoit.presentation.viewmodel.general

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BarProfileOrServicesViewModel :  ViewModel() {
    val option = MutableLiveData<Int>()

    fun sendOption(option : Int){
        this.option.value = option
    }
}