package cat.copernic.letmedoit.data.remote

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Chat
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.domain.repositories.ReportRepository
import cat.copernic.letmedoit.presentation.view.users.fragments.chat
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    @FirebaseModule.ReportsCollection val reportsCollection: CollectionReference
): ReportRepository{
    override suspend fun createReport(report: Report): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            report.id.let {
                reportsCollection.document(it).get()
                    .addOnSuccessListener { uploadStatus = true }
                    .addOnFailureListener { uploadStatus = false }
                    .await()
            }
            emit(DataState.Success(uploadStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)
    override suspend fun deleteReport(report: Report): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            report.id.let {
                reportsCollection.document(it).delete()
                    .addOnSuccessListener { uploadStatus = true }
                    .addOnFailureListener { uploadStatus = false }
                    .await()
            }
            emit(DataState.Success(uploadStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)
}