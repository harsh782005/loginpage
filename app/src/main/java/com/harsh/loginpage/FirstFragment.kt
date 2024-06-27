package com.harsh.loginpage

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.harsh.loginpage.databinding.FragmentFirstBinding
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentFirstBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
binding =FragmentFirstBinding.inflate(layoutInflater)
        return binding?.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.etbtn1?.setOnClickListener {

            if (binding?.etEmail?.text?.toString().isNullOrEmpty()) {
                binding?.etEmail?.error = "enter your mail"
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(binding?.etEmail?.text.toString()).matches()){
                binding?.etEmail?.error = "enter valid email"
            }
            else{
                val number1 = Random.nextInt(0,9)
                val number2 = Random.nextInt(0,9)
                val number3 = Random.nextInt(0,9)
                val number4 = Random.nextInt(0,9)
                val bundle = Bundle()
                bundle.putString("email",binding?.etEmail?.text?.toString())
                bundle.putString("otp","$number1$number2$number3$number4")
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment2,bundle)
                try{
                    var intent= Intent(Intent.ACTION_SEND)
                    intent.setType("text/email")
                    intent.putExtra(Intent.EXTRA_TEXT,"your otp is $number1$number2$number3$number4")
                    startActivity(intent)
                }catch (exception:Exception){
                Toast.makeText(requireContext(),"this is toast",Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}