package com.kriaactividade.xitiqueapp.ui.userresume

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kriaactividade.xitiqueapp.convertLongDateForString
import com.kriaactividade.xitiqueapp.databinding.UserResumeListRecyclerBinding
import com.kriaactividade.xitiqueapp.entity.UserEntity

class UserResumeAdapter : ListAdapter<UserEntity, ViewHolder>(UserResumeAdapter) {

    inner class ItemViewHolder(private val item:UserResumeListRecyclerBinding): ViewHolder(item.root){
        fun binding(user:UserEntity){
            item.fullName.text = user.fullname
            item.birthday.text = user.birthday.convertLongDateForString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = UserResumeListRecyclerBinding.inflate(layoutInflater)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ItemViewHolder).binding(getItem(position))
    }

    private companion object DiffUtilCallBack : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.birthday == newItem.birthday
        }

        override fun areContentsTheSame(
            oldItem: UserEntity,
            newItem: UserEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

}