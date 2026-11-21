package vn.udn.ute.quanlysinhvien.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vn.udn.ute.quanlysinhvien.data.SinhVienRepository
import vn.udn.ute.quanlysinhvien.data.local.AppDatabase
import vn.udn.ute.quanlysinhvien.data.local.SinhVien
import vn.udn.ute.quanlysinhvien.data.remote.RetrofitClient

class SinhVienViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = SinhVienRepository(
        AppDatabase.get(app).sinhVienDao(),
        RetrofitClient.api
    )

    val danhSach: StateFlow<List<SinhVien>> = repo.danhSach
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _thongBao = MutableStateFlow<String?>(null)
    val thongBao: StateFlow<String?> = _thongBao

    fun dongBo() {
        viewModelScope.launch {
            try {
                repo.dongBoTuServer()
            } catch (e: Exception) {
                _thongBao.value = "Không tải được dữ liệu: ${e.message}"
            }
        }
    }

    fun them(sv: SinhVien) {
        viewModelScope.launch { repo.them(sv) }
    }

    fun sua(sv: SinhVien) {
        viewModelScope.launch { repo.sua(sv) }
    }

    fun xoa(sv: SinhVien) {
        viewModelScope.launch { repo.xoa(sv) }
    }

    fun daHienThongBao() {
        _thongBao.value = null
    }
}