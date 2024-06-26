package com.harsh.spinnerapplication

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.harsh.spinnerapplication.databinding.FragmentSpinnerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SpinnerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpinnerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentSpinnerBinding? = null
    var mainActivity: MainActivity? = null
    var array = arrayListOf("")
lateinit var arrayAdapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpinnerBinding.inflate(layoutInflater)
        return binding?.root
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_spinner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1,
            array)
        binding?.dynamicValueSpinner?.adapter = arrayAdapter
        binding?.staticValueSpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var selectedItem = binding?.staticValueSpinner?.selectedItem as String
                    Toast.makeText(
                        requireContext(),
                        "Selected gender ${position} $selectedItem",
                        Toast.LENGTH_LONG
                    ).show()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        binding?.btnmain?.setOnClickListener {
            findNavController().navigate(R.id.action_spinnerFragment_to_list)
        }
        binding?.btn2?.setOnClickListener {
            Dialog(requireContext()).apply {
                setContentView(R.layout.custom)
                show()
                val edt1 = this.findViewById<EditText>(R.id.edt1)
                val add = this.findViewById<Button>(R.id.add)
                add?.setOnClickListener {
                    if (edt1?.text?.toString().isNullOrEmpty()) {
                        edt1?.error = "enter your city"
                    } else {
                      array.add(edt1?.text?.toString()?:"")
                        this.dismiss()
                    }
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
         * @return A new instance of fragment SpinnerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpinnerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}