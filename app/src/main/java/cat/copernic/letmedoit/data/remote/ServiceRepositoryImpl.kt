package cat.copernic.letmedoit.data.remote

import android.net.Uri
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.ServiceConstants
import cat.copernic.letmedoit.data.model.CategoryMap
import cat.copernic.letmedoit.data.model.Image
import javax.inject.Inject
import cat.copernic.letmedoit.di.FirebaseModule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import org.checkerframework.checker.units.qual.s

class ServiceRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    @FirebaseModule.ServiceCollection private val serviceCollection: CollectionReference
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
                    serviceCollection.document(service.id)
                        .collection(Constants.SERVICES_COLLECTION_IMAGES).document(index.toString())
                        .set(value, SetOptions.merge())
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
            var service : Service

            serviceCollection.document(uid).get().await().let { document ->
                service = document.toObject(Service::class.java)!!
                service.id = document.id
            }

            getServiceImages(service)
            emit(DataState.Success(service))
            emit(DataState.Finished)

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllServices(): Flow<DataState<List<Service>>> = flow {
        emit(DataState.Loading)
        try {
            val services = ArrayList<Service>()

            serviceCollection.get().await().documents.forEach { document ->
                var service = document.toObject(Service::class.java)
                if (service != null) {
                    service.id = document.id
                    services.add(service)
                }
            }

            services.forEach {
                getServiceImages(it)
            }

            emit(DataState.Success(services))
            emit(DataState.Finished)

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getServiceImages(service: Service) {
        val images = serviceCollection.document(service.id).collection(ServiceConstants.IMAGES).get().await()
                .toObjects(Image::class.java)

        service.image.removeAll(service.image.toSet())
        service.image.addAll(images)
    }

    override suspend fun updateTitle(
        idService: String,
        newTitle: String
    ): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            idService.let {
                serviceCollection.document(it).update(ServiceConstants.TITLE, newTitle)
                    .addOnSuccessListener { uploadSuccesful = true }
                    .addOnFailureListener { uploadSuccesful = false }
                    .await()
            }
            emit(DataState.Success(uploadSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateDescription(
        idService: String,
        newDescription: String
    ): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            idService.let {
                serviceCollection.document(it).update(ServiceConstants.DESCRIPTION, newDescription)
                    .addOnSuccessListener { uploadSuccesful = true }
                    .addOnFailureListener { uploadSuccesful = false }
                    .await()
            }
            emit(DataState.Success(uploadSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateCategory(
        idService: String,
        newCategoryMap: CategoryMap
    ): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            idService.let {
                serviceCollection.document(it).update(ServiceConstants.CATEGORY, newCategoryMap)
                    .addOnSuccessListener { uploadSuccesful = true }
                    .addOnFailureListener { uploadSuccesful = false }
                    .await()
            }
            emit(DataState.Success(uploadSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateNLikes(idService: String, newNum: Int): Flow<DataState<Boolean>> =
        flow {
            emit(DataState.Loading)
            try {
                var uploadSuccesful: Boolean = false
                idService.let {
                    serviceCollection.document(it).update(ServiceConstants.N_LIKES, newNum)
                        .addOnSuccessListener { uploadSuccesful = true }
                        .addOnFailureListener { uploadSuccesful = false }
                        .await()
                }
                emit(DataState.Success(uploadSuccesful))
                emit(DataState.Finished)
            } catch (e: Exception) {
                emit(DataState.Error(e))
                emit(DataState.Finished)
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateEditedTime(
        idService: String,
        newEditedTime: String
    ): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            idService.let {
                serviceCollection.document(it).update(ServiceConstants.EDITED_TIME, newEditedTime)
                    .addOnSuccessListener { uploadSuccesful = true }
                    .addOnFailureListener { uploadSuccesful = false }
                    .await()
            }
            emit(DataState.Success(uploadSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun editServiceImage(
        idService: String,
        idImg: String,
        oldFileUri: String,
        newFileURI: Uri,
        index: Int,
    ): Flow<DataState<Boolean>> = flow {

        var isSuccessful = false
        emit(DataState.Loading)

        try {
            if (deleteServiceImage(oldFileUri))
                saveServiceImage(newFileURI, idService, index)

            isSuccessful = true
            emit(DataState.Success(isSuccessful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)


    private suspend fun deleteServiceImage(oldFileUri: String): Boolean {
        var isSuccessful = false
        val sRef: StorageReference =
            FirebaseModule.storageProvider().getReferenceFromUrl(oldFileUri)

        try {
            sRef.delete()
                .addOnSuccessListener {
                    isSuccessful = true
                }
                .addOnFailureListener {
                    throw Exception(it)
                }
                .await()
        } catch (e: Exception) {
            isSuccessful = false
        }
        return isSuccessful
    }

    lateinit var uri: String

    override suspend fun saveServiceImage(
        fileURI: Uri,
        serviceId: String,
        index: Int
    ): Flow<DataState<String>> = flow {

        uri = ""
        emit(DataState.Loading)
        val sRef: StorageReference =
            storage.reference.child("serviceImages/${serviceId}/${index}")

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
}