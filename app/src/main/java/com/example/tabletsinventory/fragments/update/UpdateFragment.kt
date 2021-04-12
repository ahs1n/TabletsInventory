package com.example.tabletsinventory.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tabletsinventory.R
import com.example.tabletsinventory.model.Tablets
import com.example.tabletsinventory.viewmodel.TabletViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mTabletViewModel: TabletViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTabletViewModel = ViewModelProvider(this).get(TabletViewModel::class.java)

        view.updateTagNum.setText(args.currentTablet.tagNum)
        view.updateLocation.setText(args.currentTablet.location)
        view.updateCustodian.setText(args.currentTablet.custodian)
        view.updateAccSend.setText(args.currentTablet.accessoriesSend)
        view.updateAccRec.setText(args.currentTablet.accessoriesRec)
        view.updateDate.setText(args.currentTablet.issueDate)
        view.updateProjectName.setText(args.currentTablet.projectName)

        view.updateBtn.setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val tagNum = updateTagNum.text.toString()
        val location = updateLocation.text.toString()
        val custodian = updateCustodian.text.toString()
        val accessoriesSend = updateAccSend.text.toString()
        val accessoriesRec = updateAccRec.text.toString()
        val issueDate = updateDate.text.toString()
        val projectName = updateProjectName.text.toString()

        if (inputCheck(
                tagNum,
                location,
                custodian,
                accessoriesSend,
                accessoriesRec,
                issueDate,
                projectName
            )
        ) {
            // Create Tablet Object
            val updateTablet = Tablets(
                args.currentTablet.id,
                tagNum,
                location,
                custodian,
                accessoriesSend,
                accessoriesRec,
                issueDate,
                projectName
            )

            // Update Current Tablet
            mTabletViewModel.updateTablet(updateTablet)
            Toast.makeText(requireContext(), "Update Successfully!", Toast.LENGTH_SHORT).show()

            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
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