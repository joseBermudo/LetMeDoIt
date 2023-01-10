package cat.copernic.letmedoit.domain.usecases.reports

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.domain.repositories.ReportRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend operator fun invoke(idReport: String): Flow<DataState<Boolean>> =
        reportRepository.deleteReport(idReport)
}