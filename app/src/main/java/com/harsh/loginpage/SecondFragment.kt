package com.harsh.loginpage

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.harsh.loginpage.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentSecondBinding? = null
    var mainActivity: MainActivity? = null
    var email = ""
    var otp = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = activity as MainActivity
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            email = it.getString("email") ?: ""
            otp = it.getString("otp") ?: ""
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding?.root
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvEmail?.setText(email)
        binding?.et1?.doOnTextChanged { text, start, before, count ->
            var otp = binding?.et1?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et2?.requestFocus()
            }

        }
        binding?.et1?.onEditorAction(0)
        binding?.et2?.doOnTextChanged { _, _, _, _ ->
            var otp = binding?.et2?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et3?.requestFocus()
            }

        }
        binding?.et3?.doOnTextChanged { _, _, _, _ ->
            var otp = binding?.et3?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et4?.requestFocus()
            }
        }
        binding?.et2?.doAfterTextChanged {
            var otp = binding?.et2?.text?.toString() ?: ""
            if (otp.length == 0) {
                binding?.et1?.requestFocus()
            }
        }
        binding?.et3?.doAfterTextChanged {
            var otp = binding?.et3?.text?.toString() ?: ""
            if (otp.length == 0) {
                binding?.et2?.requestFocus()
            }
        }
        binding?.et4?.doAfterTextChanged {
            var otp = binding?.et4?.text?.toString() ?: ""
            if (otp.length == 0) {
                binding?.et3?.requestFocus()
            }
        }

        binding?.btn2?.setOnClickListener {
            var number =
                "${binding?.et1?.text?.toString()}${binding?.et2?.text?.toString()}${binding?.et3?.text?.toString()}${binding?.et4?.text?.toString()}"
            if (otp == number) {
                Dialog(requireContext()).apply {
                    setContentView(R.layout.first)
                    show()
                }
                findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
            } else {
                Dialog(requireContext()).apply {
                    setContentView(R.layout.second)
                    show()
                }
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}