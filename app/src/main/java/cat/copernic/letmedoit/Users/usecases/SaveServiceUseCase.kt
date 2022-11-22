package cat.copernic.letmedoit.Users.usecases

import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.Users.model.repository.ServiceRepository
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke(service : Service) : Flow<DataState<Service>> = serviceRepository.saveService(service)
}