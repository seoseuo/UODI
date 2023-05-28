package com.example.uodi

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uodi.databinding.AroundViewBinding

class AroundViewHolder(val binding: AroundViewBinding) : RecyclerView.ViewHolder(binding.root)

class AroundAdapter(
    val mContext: Context,
    val contents1: MutableList<Int>?,
    val contents2: MutableList<String>?,
    val contents3: MutableList<String>?,
    val contents4: MutableList<String>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class AroundViewHolder(val binding: AroundViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val currentPosition = adapterPosition
                val sData: String = contents4?.get(currentPosition).toString()

                Log.d("sData", "가게 정보에 대한 key 값 : $sData")

                val intent = Intent(binding.root.context, StoreActivity::class.java)
                intent.putExtra("sData", sData)
                intent.putExtra("sectionKey",contents4?.get(0))
                binding.root.context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AroundViewHolder(AroundViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        //홍드로이드 4분부터 context 가져오는 부분 확인해보기

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as AroundViewHolder).binding

        binding.imageView.setImageResource(contents1!![position])
        binding.titleText.text = contents2!![position]
        binding.detailText.text = contents3!![position]
        binding.viewKey.text = contents4!![position]
    }

    override fun getItemCount(): Int {
        return contents1?.size ?: 0
    }
}
