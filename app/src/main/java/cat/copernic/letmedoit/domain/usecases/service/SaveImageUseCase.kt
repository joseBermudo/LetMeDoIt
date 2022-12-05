package cat.copernic.letmedoit.domain.usecases.service

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(fileURI : Uri, serviceId : String, index : Int) = serviceRepository.saveServiceImage(fileURI,serviceId,index)
}