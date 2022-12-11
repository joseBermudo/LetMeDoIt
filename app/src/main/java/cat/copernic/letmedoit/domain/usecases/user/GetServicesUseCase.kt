package cat.copernic.letmedoit.domain.usecases.user

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.datahepers.UserServices
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetServicesUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(idUsers : String) : Flow<DataState<ArrayList<UserServices>>> = userRepository.getServices(idUsers)
}