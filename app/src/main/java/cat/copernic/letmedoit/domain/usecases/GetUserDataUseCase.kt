package cat.copernic.letmedoit.domain.usecases

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(): Flow<DataState<Boolean>> = loginRepository.getUserData()

}