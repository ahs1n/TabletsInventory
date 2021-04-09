package com.example.tabletsinventory.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tabletsinventory.R
import com.example.tabletsinventory.model.Tablets
import com.example.tabletsinventory.viewmodel.TabletViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mTabletViewModel: TabletViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mTabletViewModel = ViewModelProvider(this).get(TabletViewModel::class.java)

        view.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val tagNum = addTagNum.text.toString()
        val location = addLocation.text.toString()
        val custodian = addCustodian.text.toString()
        val accessoriesSend = addAccSend.text.toString()
        val accessoriesRec = addAccRec.text.toString()
        val issueDate = addDate.text.toString()
        val projectName = addProjectName.text.toString()

        if (inputCheck(
                tagNum,
                location,
                custodian,
                accessoriesSend,
                accessoriesRec,
                issueDate,
                projectName
            )
        ){
            // Create Tablet Object
            val tablets = Tablets(0, tagNum, location, custodian, accessoriesSend, accessoriesRec, issueDate, projectName)

            // Add Data to Database
            mTabletViewModel.addTablet(tablets)
            Toast.makeText(requireContext(), "Successfully Added!",Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill all fileds.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(
        tagNum: String,
        location: String,
        custodian: String,
        accessoriesSend: String,
        accessoriesRec: String,
        issueDate: String,
        projectName: String
    ): Boolean {
        return !(TextUtils.isEmpty(tagNum) && TextUtils.isEmpty(location) && TextUtils.isEmpty(
            custodian
        ) && TextUtils.isEmpty(accessoriesSend) && TextUtils.isEmpty(accessoriesRec) && TextUtils.isEmpty(
            issueDate
        ) && TextUtils.isEmpty(projectName))
    }
}