package cat.copernic.letmedoit.domain.usecases.reports

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.domain.repositories.ReportRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
){
    suspend operator fun invoke(report: Report): Flow<DataState<Boolean>> = reportRepository.createReport(report)
}