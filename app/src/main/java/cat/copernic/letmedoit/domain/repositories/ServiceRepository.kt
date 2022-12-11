package cat.copernic.letmedoit.domain.repositories

import android.net.Uri
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.datahepers.CategoryMap
import kotlinx.coroutines.flow.Flow


interface ServiceRepository {

    //add
    suspend fun saveService(service : Service) : Flow<DataState<Service>>
    suspend fun saveServiceImage(fileURI : Uri, serviceId : String,index : Int): Flow<DataState<String>>
    //get
    suspend fun getService(uid : String) : Flow<DataState<Service>>
    suspend fun getAllServices() : Flow<DataState<List<Service>>>

    //delete
    suspend fun removeImage(idService: String, imgIndex: Int,imgLink : String) : Flow<DataState<Boolean>>

    //update
    suspend fun updateTitle(idService : String, newTitle : String): Flow<DataState<Boolean>>
    suspend fun updateDescription(idService : String, newDescription : String): Flow<DataState<Boolean>>
    suspend fun updateCategory(idService : String, newCategoryMap: CategoryMap): Flow<DataState<Boolean>>
    suspend fun updateNLikes(idService : String, newNum : Int): Flow<DataState<Boolean>>
    suspend fun updateEditedTime(idService : String, newEditedTime : String): Flow<DataState<Boolean>>
    suspend fun editServiceImage(idService : String,newFileURI : Uri, index: Int): Flow<DataState<String>>

}