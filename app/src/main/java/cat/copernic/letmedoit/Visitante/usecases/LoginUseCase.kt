package cat.copernic.letmedoit.Visitante.usecases

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Visitante.model.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<DataState<Boolean>> = loginRepository.login(email,password)
}