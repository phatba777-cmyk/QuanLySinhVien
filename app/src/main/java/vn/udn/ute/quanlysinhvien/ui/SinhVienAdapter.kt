package vn.udn.ute.quanlysinhvien.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vn.udn.ute.quanlysinhvien.data.local.SinhVien
import vn.udn.ute.quanlysinhvien.databinding.ItemSinhVienBinding

class SinhVienAdapter(
    private val onClick: (SinhVien) -> Unit,
    private val onLongClick: (SinhVien) -> Unit
) : ListAdapter<SinhVien, SinhVienAdapter.VH>(Diff) {

    class VH(val b: ItemSinhVienBinding) : RecyclerView.ViewHolder(b.root)

    object Diff : DiffUtil.ItemCallback<SinhVien>() {
        override fun areItemsTheSame(oldItem: SinhVien, newItem: SinhVien): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SinhVien, newItem: SinhVien): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemSinhVienBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val sv = getItem(position)
        holder.b.tvTen.text = sv.ten
        holder.b.tvMsv.text = "MSV: " + sv.msv
        holder.b.tvLop.text = "Lớp: " + sv.lop
        holder.itemView.setOnClickListener { onClick(sv) }
        holder.itemView.setOnLongClickListener {
            onLongClick(sv)
            true
        }
    }
}