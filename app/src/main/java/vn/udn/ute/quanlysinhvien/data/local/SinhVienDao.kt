package vn.udn.ute.quanlysinhvien.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SinhVienDao {

    @Query("SELECT * FROM sinh_vien ORDER BY id DESC")
    fun layTatCa(): Flow<List<SinhVien>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun them(sv: SinhVien)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun themNhieu(ds: List<SinhVien>)

    @Update
    suspend fun sua(sv: SinhVien)

    @Delete
    suspend fun xoa(sv: SinhVien)
}