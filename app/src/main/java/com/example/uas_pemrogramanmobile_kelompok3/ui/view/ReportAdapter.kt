package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_pemrogramanmobile_kelompok3.R
import com.example.uas_pemrogramanmobile_kelompok3.data.model.Report
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ItemReportBinding

class ReportAdapter(private val reports: List<Report>) :
    RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    class ReportViewHolder(val binding: ItemReportBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reports[position]
        holder.binding.apply {
            tvCandidateName.text = report.candidateName
            tvPosition.text = report.position
            tvScore.text = report.finalScore.toString()
            tvScoreLabel.text = holder.itemView.context.getString(R.string.label_final_score, report.finalScore)
        }
    }

    override fun getItemCount(): Int = reports.size
}