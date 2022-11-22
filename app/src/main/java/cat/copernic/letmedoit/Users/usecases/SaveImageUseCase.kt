package cat.copernic.letmedoit.Users.usecases

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import cat.copernic.letmedoit.Users.model.repository.ServiceRepository
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(activity : Activity, fileURI : Uri, serviceId : String, fragment: Fragment, index : Int) = serviceRepository.saveServiceImage(activity,fileURI,serviceId,fragment,index)
}