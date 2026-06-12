package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_pemrogramanmobile_kelompok3.R
import com.example.uas_pemrogramanmobile_kelompok3.data.model.Candidate
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ItemCandidateBinding

class CandidateAdapter(private val candidates: List<Candidate>) :
    RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder>() {

    class CandidateViewHolder(val binding: ItemCandidateBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val binding = ItemCandidateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CandidateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        val candidate = candidates[position]
        holder.binding.apply {
            tvCandidateName.text = candidate.name
            tvPosition.text = candidate.position
            chipStatus.text = candidate.status
            progressIndicator.progress = candidate.progress
            
            // Status Styling
            when (candidate.status) {
                "Active" -> chipStatus.setChipBackgroundColorResource(R.color.warning)
                "Completed" -> chipStatus.setChipBackgroundColorResource(R.color.success)
                else -> chipStatus.setChipBackgroundColorResource(R.color.secondary_container)
            }
        }
    }

    override fun getItemCount(): Int = candidates.size
}