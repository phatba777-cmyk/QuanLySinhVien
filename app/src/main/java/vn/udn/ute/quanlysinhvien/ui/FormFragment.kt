package vn.udn.ute.quanlysinhvien.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import vn.udn.ute.quanlysinhvien.data.local.SinhVien
import vn.udn.ute.quanlysinhvien.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    private val vm: SinhVienViewModel by activityViewModels()

    private var svId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        svId = arguments?.getInt(ARG_ID) ?: 0

        if (svId != 0) {
            val sv = vm.danhSach.value.firstOrNull { it.id == svId }
            if (sv != null) {
                binding.edtTen.setText(sv.ten)
                binding.edtMsv.setText(sv.msv)
                binding.edtLop.setText(sv.lop)
            }
        }

        binding.btnLuu.setOnClickListener { luu() }
    }

    private fun luu() {
        val ten = binding.edtTen.text.toString().trim()
        val msv = binding.edtMsv.text.toString().trim()
        val lop = binding.edtLop.text.toString().trim()

        if (ten.isEmpty() || msv.isEmpty() || lop.isEmpty()) {
            Toast.makeText(requireContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        if (svId == 0) {
            vm.them(SinhVien(ten = ten, msv = msv, lop = lop))
        } else {
            vm.sua(SinhVien(id = svId, ten = ten, msv = msv, lop = lop))
        }
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ID = "id"

        fun newInstance(id: Int): FormFragment {
            val fragment = FormFragment()
            fragment.arguments = bundleOf(ARG_ID to id)
            return fragment
        }
    }
}