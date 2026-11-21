package vn.udn.ute.quanlysinhvien

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.udn.ute.quanlysinhvien.databinding.ActivityMainBinding
import vn.udn.ute.quanlysinhvien.ui.DanhSachFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DanhSachFragment())
                .commit()
        }
    }
}