package cat.copernic.letmedoit.Visitante.usecases

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Visitante.model.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke() : Flow<DataState<Boolean>> = loginRepository.logOut()
}