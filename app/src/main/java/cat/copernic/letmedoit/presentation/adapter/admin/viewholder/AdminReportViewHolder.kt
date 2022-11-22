package cat.copernic.letmedoit.presentation.adapter.admin.viewholder

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.databinding.ItemAdminReportListBinding

class AdminReportViewHolder(val binding: ItemAdminReportListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val userA = binding.txtUser
    val userReported = binding.txtUserReported

    fun render(
        reportModel: Report
    ) {
        userA.text = reportModel.users.userOneId
        userReported.text = reportModel.users.userTwoId
    }
}