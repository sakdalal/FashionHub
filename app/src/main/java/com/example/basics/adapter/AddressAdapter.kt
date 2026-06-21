package com.example.basics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basics.databinding.AddAddressBinding
import com.example.basics.db.AddressEntity
import com.example.basics.event.AddressEvent

class AddressAdapter(private val onEvent: (AddressEvent) -> Unit): ListAdapter<AddressEntity, AddressAdapter.MyViewHolder>(AddressDiffCallBack()){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = AddAddressBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        return holder.bind(getItem(position),onEvent)
    }


    class MyViewHolder(val binding: AddAddressBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: AddressEntity,onEvent: (AddressEvent) -> Unit){
            binding.mobile.text=item.mobile
            binding.name.text=item.name
            binding.house.text=item.house
            binding.address.text=item.address
            binding.city.text=item.city
            binding.pincode.text=item.pincode
            binding.state.text=item.state
            binding.homeOrOffice.text=item.addressType


            binding.editDeleteAddress.editBoxText.setOnClickListener {
                onEvent(AddressEvent.Edit(item))
            }

            binding.editDeleteAddress.removeBoxText.setOnClickListener {

                onEvent(AddressEvent.Delete(item))
            }

            binding.makeDefault.setOnClickListener {
                onEvent(AddressEvent.SetDefault(item))
            }

            binding.root.setOnClickListener {
                binding.clickVisible.visibility = View.VISIBLE
                binding.editDeleteAddress.root.visibility = View.VISIBLE
            }

        }
    }

}