package cat.copernic.letmedoit.General.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BarProfileOrServicesViewModel :  ViewModel() {
    val option = MutableLiveData<Int>()

    fun sendOption(option : Int){
        this.option.value = option
    }
}