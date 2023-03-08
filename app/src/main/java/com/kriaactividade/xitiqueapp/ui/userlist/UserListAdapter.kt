package com.kriaactividade.xitiqueapp.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kriaactividade.xitiqueapp.convertLongDateForString
import com.kriaactividade.xitiqueapp.databinding.UserHeaderListRecyclerBinding
import com.kriaactividade.xitiqueapp.databinding.UserItemListRecyclerBinding
import com.kriaactividade.xitiqueapp.entity.UserForAdapter

class UserListAdapter : ListAdapter<UserForAdapter, ViewHolder>(UserListAdapter) {
    private val sectionHeader = 0
    private val itemList = 1

    inner class UserItemViewHolder(private val userItem: UserItemListRecyclerBinding) : ViewHolder(userItem.root) {
        fun binding(item: UserForAdapter.Item) {
            userItem.fullName.text = item.fullname
            userItem.birthday.text = item.birthday.convertLongDateForString()
        }
    }

    inner class HeaderItemViewHolder(private val userItem: UserHeaderListRecyclerBinding) : ViewHolder(userItem.root) {
        fun binding(item: UserForAdapter.Header) {
            userItem.header.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == sectionHeader){
            val header = UserHeaderListRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent,false)
            HeaderItemViewHolder(header)
        } else {
            val item = UserItemListRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            UserItemViewHolder(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is UserForAdapter.Header -> sectionHeader
            is UserForAdapter.Item -> itemList
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(val user = getItem(position)){
            is UserForAdapter.Header -> (holder as HeaderItemViewHolder).binding(user)
            is UserForAdapter.Item -> (holder as UserItemViewHolder).binding(user)
        }
    }

    private companion object DiffUtilCallBack : DiffUtil.ItemCallback<UserForAdapter>() {
        override fun areItemsTheSame(oldItem: UserForAdapter, newItem: UserForAdapter): Boolean {
            return when(oldItem){
                 is UserForAdapter.Header -> oldItem.description == (newItem as UserForAdapter.Header).description
                 is UserForAdapter.Item -> oldItem.id == (newItem as UserForAdapter.Item).id
             }
        }

        override fun areContentsTheSame(
            oldItem: UserForAdapter,
            newItem: UserForAdapter
        ): Boolean {
            return when(oldItem){
                is UserForAdapter.Header -> oldItem == (newItem as UserForAdapter.Header)
                is UserForAdapter.Item -> oldItem == (newItem as UserForAdapter.Item)
            }
        }
    }

}