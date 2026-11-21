package vn.udn.ute.quanlysinhvien.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sinh_vien")
data class SinhVien(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ten: String,
    val msv: String,
    val lop: String
)