package vn.udn.ute.quanlysinhvien.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import vn.udn.ute.quanlysinhvien.R
import vn.udn.ute.quanlysinhvien.databinding.FragmentDanhSachBinding

class DanhSachFragment : Fragment() {

    private var _binding: FragmentDanhSachBinding? = null
    private val binding get() = _binding!!
    private val vm: SinhVienViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDanhSachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = SinhVienAdapter(
            onClick = { sv -> moForm(sv.id) },
            onLongClick = { sv ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Xóa sinh viên")
                    .setMessage("Xóa " + sv.ten + "?")
                    .setPositiveButton("Xóa") { _, _ -> vm.xoa(sv) }
                    .setNegativeButton("Hủy", null)
                    .show()
            }
        )
        binding.rvSinhVien.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSinhVien.adapter = adapter
        binding.btnThem.setOnClickListener { moForm(0) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    vm.danhSach.collect { adapter.submitList(it) }
                }
                launch {
                    vm.thongBao.collect { msg ->
                        if (msg != null) {
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                            vm.daHienThongBao()
                        }
                    }
                }
            }
        }

        vm.dongBo()
    }

    private fun moForm(id: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, FormFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}