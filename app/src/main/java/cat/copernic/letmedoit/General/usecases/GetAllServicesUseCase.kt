package cat.copernic.letmedoit.General.usecases

import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.Users.model.repository.ServiceRepository
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllServicesUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke() : Flow<DataState<List<Service>>> = serviceRepository.getAllServices()
}