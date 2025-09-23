package com.example.fatimahnita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SuratAdapter(
    private val suratList: MutableList<Surat>,
    private val onEdit: (Surat) -> Unit,
    private val onDelete: (Surat) -> Unit
) : RecyclerView.Adapter<SuratAdapter.SuratViewHolder>() {

    class SuratViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvKeterangan: TextView = itemView.findViewById(R.id.tvKeterangan)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuratViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return SuratViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuratViewHolder, position: Int) {
        val surat = suratList[position]
        holder.tvNama.text = surat.nama
        holder.tvKeterangan.text = surat.keterangan

        holder.btnEdit.setOnClickListener { onEdit(surat) }
        holder.btnHapus.setOnClickListener { onDelete(surat) }
    }

    override fun getItemCount(): Int = suratList.size
}
