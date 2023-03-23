package com.example.machineest.ui.adapter

import com.example.machineest.data.models.User

interface OnClickItemListener {
    fun onClickItem(item: User, position:Int)
}