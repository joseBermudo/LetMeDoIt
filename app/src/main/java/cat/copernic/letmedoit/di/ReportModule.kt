package cat.copernic.letmedoit.di

import cat.copernic.letmedoit.data.remote.ReportRepositoryImpl
import cat.copernic.letmedoit.domain.repositories.ReportRepository
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
object ReportModule {
    //Provider que proporciona un Singleton el cual permite obtener una implementación del login repository.
    @Provides
    @Singleton
    fun reportRepositoryProvider(
        @FirebaseModule.ReportsCollection reportsCollection: CollectionReference
    ): ReportRepository {
        return ReportRepositoryImpl(reportsCollection)
    }
}