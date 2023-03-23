package com.example.machineest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.machineest.R
import com.example.machineest.data.models.User
import com.example.machineest.databinding.UserListItemBinding

class UserListAdapter(private val listener: OnClickItemListener):PagingDataAdapter<User,UserListViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {

        getItem(position)?.let { holder.bind(it,position,listener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
      return  UserListViewHolder(
          UserListItemBinding.inflate(
              LayoutInflater.from(parent.context),parent,false
          )
      )
    }

    companion object{
        val diffCallback=object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id==newItem.id
            }

        }
    }


}
class UserListViewHolder(
   private val  binding:UserListItemBinding
):RecyclerView.ViewHolder(binding.root){
fun bind(item: User, position: Int, listener: OnClickItemListener){

    Glide.with(itemView)
        .load(item?.image)
        .centerCrop()
        .placeholder(R.drawable.loading)
        .error(R.drawable.loading)
        .fallback(R.drawable.loading)
        .into(binding.imgUser)

    binding.txtUserName.text="${item.firstName} ${item.lastName} ${item.maidenName}"

    binding.root.setOnClickListener {
     listener.onClickItem(item,position)
    }
}
}