package cat.copernic.letmedoit.Admin.di

import cat.copernic.letmedoit.Admin.data.remote.CategoryRepositoryImpl
import cat.copernic.letmedoit.Admin.repository.CategoryRepository
import cat.copernic.letmedoit.Utils.di.FirebaseModule
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        @FirebaseModule.CategoryCollection categoryCollection: CollectionReference
    ): CategoryRepository{
        return CategoryRepositoryImpl(
            categoryCollection
        )
    }
}