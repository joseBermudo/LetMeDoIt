package cat.copernic.letmedoit.Visitante.usecases

import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Visitante.model.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(user : Users, password : String): Flow<DataState<Users>> = loginRepository.signUp(user,password)
}