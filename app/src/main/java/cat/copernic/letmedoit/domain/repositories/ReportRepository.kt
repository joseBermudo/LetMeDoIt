package cat.copernic.letmedoit.domain.repositories

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.data.model.Report
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun createReport(report: Report): Flow<DataState<Boolean>>
    suspend fun deleteReport(report: Report): Flow<DataState<Boolean>>
    suspend fun getReports(): Flow<DataState<List<Report>>>
}