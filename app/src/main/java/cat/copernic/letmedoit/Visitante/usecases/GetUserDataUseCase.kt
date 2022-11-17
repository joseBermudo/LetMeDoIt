package cat.copernic.letmedoit.Visitante.usecases

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Visitante.model.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(): Flow<DataState<Boolean>> = loginRepository.getUserData()

}