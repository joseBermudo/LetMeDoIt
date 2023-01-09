package cat.copernic.letmedoit.presentation.viewmodel.general

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfilesServicesManagerViewModel : ViewModel() {
    val pagePosition = MutableLiveData<Int>()

    fun sendPagePosition(pagePosition: Int) {
        this.pagePosition.value = pagePosition
    }
}