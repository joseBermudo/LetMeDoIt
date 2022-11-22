package cat.copernic.letmedoit.di

import cat.copernic.letmedoit.Utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

//Module -> Encargado de construir dependencias
//Lo instalamos como un singleton, de esta forma es la misma clase para toda la app
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    //Componente Singleton que provee el FireBaseAuth
    @Provides
    @Singleton
    fun firebaseAuthProvider() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    //Componente Singleton que provee la instacia de Firebase Firestiore
    @Provides
    @Singleton
    fun firestoreProvider() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @UsersCollection
    @Provides
    @Singleton
    fun usersProvider(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.USERS_COLLECTION)
    }

    @ServiceCollection
    @Provides
    @Singleton
    fun serviceProvider(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.SERVICES_COLLECTION)
    }

    @CategoryCollection
    @Provides
    @Singleton
    fun categoryProvider(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.CATEGORIES_COLLECTION)
    }

    @ReportsCollection
    @Provides
    @Singleton
    fun reportsProvider(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.REPORTS_COLLECTION)
    }

    @ReportsReasonCollection
    @Provides
    @Singleton
    fun reasonReportsCollection(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.REASONS_REPORTS_COLLECTION)
    }

    @DealCollection
    @Provides
    @Singleton
    fun dealCollection(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.DEALS_COLLECTION)
    }

    @ChatCollection
    @Provides
    @Singleton
    fun chatCollection(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.CHATS_COLLECTION)
    }

    //Annotation Class --> Utilizada para identificar un binding especifico para un tipo cuandoe ste tipo tiene multiples bindings definidos
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsersCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ServiceCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CategoryCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ReportsCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ReportsReasonCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DealCollection

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ChatCollection


}
