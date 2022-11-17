package cat.copernic.letmedoit.General.usecases

import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.Users.model.repository.ServiceRepository
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke(uid : String) : Flow<DataState<Service>> = serviceRepository.getService(uid)
}