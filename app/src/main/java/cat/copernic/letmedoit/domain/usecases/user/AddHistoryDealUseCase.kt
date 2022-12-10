package cat.copernic.letmedoit.domain.usecases.user

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddHistoryDealUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(idUserOne: String,idUserTwo : String, idDeal : String) : Flow<DataState<Boolean>> = userRepository.addHistoryDeal(idUserOne,idUserTwo,idDeal)
}