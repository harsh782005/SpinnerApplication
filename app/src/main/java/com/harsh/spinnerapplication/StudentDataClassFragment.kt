package com.harsh.spinnerapplication

import android.R
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.harsh.spinnerapplication.databinding.Custom2Binding
import com.harsh.spinnerapplication.databinding.Custom3Binding
import com.harsh.spinnerapplication.databinding.Custom4Binding
import com.harsh.spinnerapplication.databinding.FragmentStudentDataClassBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDataClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDataClassFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var studentarray = arrayListOf<StudentDataClass>()
    var binding: FragmentStudentDataClassBinding? = null
    var adapter = StudentAdapter(studentarray)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }

        studentarray.add(StudentDataClass(1, "Harsh", "kotlin"))
        studentarray.add(StudentDataClass(2, "Tanveer", "kotlin"))
        studentarray.add(StudentDataClass(3, "Vanshika", "kotlin"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDataClassBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listView?.adapter = adapter
        binding?.btnfab?.setOnClickListener {

            val dialogBinding = Custom3Binding.inflate(layoutInflater)

            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                show()
            }
            dialogBinding.btnUpdate.setOnClickListener {
                if (dialogBinding.Name.text.toString().isNullOrEmpty()) {
                    dialogBinding.Name.error = " enter your name"
                }

                if (dialogBinding.Course.text.toString().isNullOrEmpty()) {
                    dialogBinding.Course.error = " enter your course"
                }
                if (dialogBinding.RollNo.text.toString().isNullOrEmpty()) {
                    dialogBinding.RollNo.error = " enter your roll no"
                } else {

                    studentarray.add(
                        StudentDataClass(
                            dialogBinding.Name.text.toString().toInt(),
                            dialogBinding.RollNo.text?.toString(),
                            dialogBinding.Course.text?.toString()
                        )
                    )

                    dialog.dismiss()
                }
            }
        }
        binding?.listView?.setOnItemClickListener { adapterView, view, i, l ->
            val dialogBinding = Custom4Binding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                getWindow()?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                show()
            }
            dialogBinding.update.setOnClickListener {
                if (dialogBinding.name.text.toString().trim().isNullOrEmpty()) {
                    // dialogBinding.name.error = resources.getString(R.string.name)
                    dialogBinding.name.error = "enter your name"
                } else if (dialogBinding.rollno.text.toString().trim().isNullOrEmpty()) {
                    dialogBinding.rollno.error = "enter your roll no"
                } else if (dialogBinding.course.text.toString().trim().isNullOrEmpty()) {
                    dialogBinding.course.error = "enter your course"
                } else {
                    studentarray.set(
                        i, StudentDataClass(
                            dialogBinding.name.text.toString().toInt(),
                            dialogBinding.rollno.text.toString(),
                            dialogBinding.course.text.toString()
                                    
                        )
                    )
                    dialog.dismiss()
                }

            }
        }
        binding?.listView?.setOnItemLongClickListener { adapterView, view, i, l ->
            var alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("do you want to delete")
            alertDialog.setPositiveButton("yes") { _, _ ->
                studentarray.removeAt(i)
                adapter.notifyDataSetChanged()

            }
            alertDialog.setNegativeButton("No") { _, _ ->
            }
            alertDialog.show()
            return@setOnItemLongClickListener true


        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentDataClassFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentDataClassFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}