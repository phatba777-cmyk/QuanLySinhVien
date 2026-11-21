package vn.udn.ute.quanlysinhvien.data

import kotlinx.coroutines.flow.Flow
import vn.udn.ute.quanlysinhvien.data.local.SinhVien
import vn.udn.ute.quanlysinhvien.data.local.SinhVienDao
import vn.udn.ute.quanlysinhvien.data.remote.SinhVienApi

class SinhVienRepository(
    private val dao: SinhVienDao,
    private val api: SinhVienApi
) {

    val danhSach: Flow<List<SinhVien>> = dao.layTatCa()

    suspend fun dongBoTuServer() {
        val ds = api.layDanhSach().map {
            SinhVien(
                id = it.id,
                ten = it.name,
                msv = "SV" + it.id.toString().padStart(3, '0'),
                lop = it.email
            )
        }
        dao.themNhieu(ds)
    }

    suspend fun them(sv: SinhVien) = dao.them(sv)

    suspend fun sua(sv: SinhVien) = dao.sua(sv)

    suspend fun xoa(sv: SinhVien) = dao.xoa(sv)
}