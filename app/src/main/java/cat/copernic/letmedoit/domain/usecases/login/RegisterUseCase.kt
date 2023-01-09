package cat.copernic.letmedoit.domain.usecases.login

import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(user : Users, password : String): Flow<DataState<Users>> = loginRepository.signUp(user,password)
}