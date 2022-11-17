package cat.copernic.letmedoit.General.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BarChatsOrDealsViewModel:  ViewModel()  {
    val option = MutableLiveData<Int>()

    fun sendOption(option : Int){
        this.option.value = option
    }
}