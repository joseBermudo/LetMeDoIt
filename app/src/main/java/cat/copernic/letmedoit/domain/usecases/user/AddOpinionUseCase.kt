package cat.copernic.letmedoit.domain.usecases.user

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Opinion
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddOpinionUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(opinion: Opinion,idUser : String) : Flow<DataState<Boolean>> = userRepository.addOpinion(opinion,idUser)
}