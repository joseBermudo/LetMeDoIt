package cat.copernic.letmedoit.data.remote

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import cat.copernic.letmedoit.Utils.DataState
import javax.inject.Inject
import cat.copernic.letmedoit.di.FirebaseModule
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class ServiceRepositoryImpl @Inject constructor(
    @FirebaseModule.ServiceCollection val serviceCollection: CollectionReference
) : ServiceRepository {

    override suspend fun saveService(service: Service): Flow<DataState<Service>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            //Guardamos el usuario, pero si ya existe un documento con estos datos hace un merge sobreescrbiendo los datos.
            service.id.let {
                serviceCollection.document(it).set(service, SetOptions.merge())
                    .addOnSuccessListener { uploadSuccesful = true }
                    .addOnFailureListener { uploadSuccesful = false }
                    .await()

                for ((index, value) in service.image.withIndex()) {
                    serviceCollection.document(service.id).collection(Constants.SERVICES_COLLECTION_IMAGES).document(index.toString()).set(value, SetOptions.merge())
                }
            }
            if (!uploadSuccesful)
                throw Exception("Error Uploading Service")

            emit(DataState.Success(service))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun getService(uid: String): Flow<DataState<Service>> = flow {
        emit(DataState.Loading)
        try {
            val service =
                serviceCollection.document(uid).get().await().toObject(Service::class.java)
                    ?: throw Exception("ID DOES NOT EXIST")
            emit(DataState.Success(service))
            emit(DataState.Finished)

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllServices(): Flow<DataState<List<Service>>> = flow {
        emit(DataState.Loading)
        try {
            val services = serviceCollection.get().await().toObjects(Service::class.java)
            emit(DataState.Success(services))
            emit(DataState.Finished)

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)


    lateinit var uri: String
    override suspend fun saveServiceImage(
        activity: Activity,
        fileURI: Uri,
        serviceId: String,
        fragment: Fragment,
        index: Int
    ): Flow<DataState<String>> = flow {

        uri = ""
        emit(DataState.Loading)
        val sRef: StorageReference =
            FirebaseStorage.getInstance().reference.child("serviceImages/${serviceId}/${index}")

        try {
            val downloadUrl = sRef.putFile(fileURI)
                .addOnFailureListener { throw Exception(it) }.await()
                .storage.downloadUrl.await()

            uri = downloadUrl.toString()

            emit(DataState.Success(uri))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun test(taskSnapshot: UploadTask.TaskSnapshot): Task<Uri> {
        return taskSnapshot.metadata!!.reference!!.downloadUrl
    }
}