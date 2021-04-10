package com.example.tabletsinventory.fragments.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabletsinventory.R
import com.example.tabletsinventory.model.Tablets
import com.example.tabletsinventory.viewmodel.TabletViewModel
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var adapter: ListAdapter
    private lateinit var mTabletViewModel: TabletViewModel
    var items: List<Tablets> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }
        /*
        * Viewmodel initialize
        * */
        mTabletViewModel = ViewModelProvider(this).get(TabletViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recyclerview
        adapter = ListAdapter()
        recyclerview.adapter = adapter

        // TabletViewModel
        multiStateView.viewState = MultiStateView.ViewState.LOADING
        mTabletViewModel.readAllData.observe(viewLifecycleOwner, { tablet ->
            tablet?.let {
                if (it.isNotEmpty()) {
                    val item =
                        it.map { root -> root.copy(projectName = root.projectName.toLowerCase(Locale.ENGLISH)) }
                    items = item
                    adapter.setData(items)
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                } else multiStateView.viewState = MultiStateView.ViewState.EMPTY
            }
        })

        /*
        * Filter listener
        * */
        txtFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                multiStateView.viewState = MultiStateView.ViewState.LOADING
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null) {
                    lifecycleScope.launch {
                        delay(1000)
                        adapter.tabletList = items
                        multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    }
                } else {
                    lifecycleScope.launch {
                        delay(1000)
                        val cropItem = items
                        adapter.tabletList = cropItem.sortedBy { it.projectName }.filter {
                            it.projectName.startsWith(
                                s.toString().toLowerCase(
                                    Locale.ENGLISH
                                )
                            )
                        } as ArrayList<Tablets>
                        if (adapter.tabletList.isNotEmpty()) {
                            adapter.notifyDataSetChanged()
                            multiStateView.viewState = MultiStateView.ViewState.CONTENT
                        } else
                            multiStateView.viewState = MultiStateView.ViewState.EMPTY
                    }
                }
            }

        })

    }
}