package cat.copernic.letmedoit.Admin.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Report
import cat.copernic.letmedoit.databinding.ItemAdminReportListBinding

class AdminReportAdapter(
    val reportList: List<Report> ,
) : RecyclerView.Adapter<AdminReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminReportViewHolder {
        val binding =
            ItemAdminReportListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminReportViewHolder, position: Int) {
        val item = reportList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = reportList.size

}