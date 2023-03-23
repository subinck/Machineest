package com.example.machineest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.machineest.R
import com.example.machineest.data.models.User
import com.example.machineest.databinding.FragmentHomeBinding
import com.example.machineest.ui.adapter.OnClickItemListener
import com.example.machineest.ui.adapter.UserListAdapter
import com.example.machineest.ui.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(),OnClickItemListener{
    private lateinit var binding:FragmentHomeBinding
    private var adapter: UserListAdapter? = null
    private lateinit var viewModel:HomeFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        initView()
        collectUiState()
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback)
    }
    private fun initView() {
        binding.appBar.appbarBack.visibility=View.GONE
        adapter = UserListAdapter(this)
        binding.rvUserList.adapter = adapter
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadUsersByPage().collectLatest {
                adapter?.submitData(it)

            }

            lifecycleScope.launch {
                //Your adapter's loadStateFlow here
                adapter?.loadStateFlow?.distinctUntilChangedBy {
                    it.refresh
                }?.collect {
                    //you get all the data here
                    val list = adapter?.snapshot()
                    Log.d("LL", "$list")

                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    override fun onClickItem(item: User, position: Int) {
        val bundle = Bundle()
        bundle.putInt("id", item.id?:0)
        view?.let { Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_userDetailsFragment, bundle) }
    }

}