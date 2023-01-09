package cat.copernic.letmedoit.domain.usecases.user

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCurriculumUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(pdkLink : String) : Flow<DataState<Boolean>> = userRepository.updateCurriculum(pdkLink)
}