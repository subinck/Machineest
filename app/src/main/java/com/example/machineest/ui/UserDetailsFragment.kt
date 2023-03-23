package com.example.machineest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.machineest.R
import com.example.machineest.databinding.FragmentUserDetailsBinding
import com.example.machineest.ui.viewmodel.UserDetailsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding:FragmentUserDetailsBinding
    private  var userId:Int=0
    private lateinit var viewModel: UserDetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_user_details, container, false)
        viewModel = ViewModelProvider(this)[UserDetailsFragmentViewModel::class.java]
        binding.appBar.appbarHeading.text=getString(R.string.user_details)
        arguments.let {
          userId=  it?.getInt("id")?:0
        }
        eventListeners()
        setUserDetails()
        return binding.root
    }
    private fun eventListeners(){
        binding.appBar.appbarBack.setOnClickListener {
            view?.let { it1 -> Navigation.findNavController(it1).popBackStack() }
        }
    }
    private fun  setUserDetails(){

          viewModel.getLiveDataObserver().observe(viewLifecycleOwner){user->
              Glide.with(binding.root)
                  .load(user?.image)
                  .centerCrop()
                  .placeholder(R.drawable.loading)
                  .error(R.drawable.loading)
                  .fallback(R.drawable.loading)
                  .into(binding.imgUser)

              binding.txtName.text="${user.firstName} ${user.lastName} ${user.maidenName}"
              binding.txtAge.text= user?.age.toString()
              binding.txtBirthdate.text=user.birthDate
              binding.txtPhone.text=user.phone
              binding.txtEmail.text=user.email
          }
        viewModel.loadUserDetails(userId)
    }


}