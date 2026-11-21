package vn.udn.ute.quanlysinhvien.data.remote

import retrofit2.http.GET

data class SinhVienDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)

interface SinhVienApi {

    @GET("users")
    suspend fun layDanhSach(): List<SinhVienDto>
}