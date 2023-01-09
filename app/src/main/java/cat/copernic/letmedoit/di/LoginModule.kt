package cat.copernic.letmedoit.di

import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.data.remote.LoginRepositoryImpl
import cat.copernic.letmedoit.domain.repositories.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Module -> Encargado de construir dependencias
//Lo instalamos como un singleton, de esta forma es la misma clase para toda la app
@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    //Provider que proporciona un Singleton el cual permite obtener una implementación del login repository.
    @Provides
    @Singleton
    fun loginRepositoryProvider(
        auth : FirebaseAuth,
        @FirebaseModule.UsersCollection usersCollection: CollectionReference
    ): LoginRepository {
        return LoginRepositoryImpl(auth,usersCollection)
    }
}