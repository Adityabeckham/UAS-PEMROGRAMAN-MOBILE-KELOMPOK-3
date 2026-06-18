package com.example.uas_pemrogramanmobile_kelompok3.ui.adapter

import android.content.Intent
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
        val context = holder.itemView.context
        
        holder.binding.apply {
            tvCandidateName.text = candidate.name
            tvPosition.text = candidate.position
            
            // Task 2.3 (Max): Display Token and Share Functionality
            tvTokenLabel.text = context.getString(R.string.label_token, candidate.token)
            
            btnShareToken.setOnClickListener {
                val shareText = context.getString(R.string.share_token_text, candidate.name, candidate.token)
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }
                context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_title)))
            }

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