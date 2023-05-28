package kr.ac.hallym.seoseuofolio

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uodi.QRActivity
import com.example.uodi.databinding.CouponViewBinding

class CouponViewHolder(val binding: CouponViewBinding) : RecyclerView.ViewHolder(binding.root)

class CouponAdapter(
    val mContext: Context,
    val cName: MutableList<String>?,
    val cID: MutableList<Int>?

) : RecyclerView.Adapter<CouponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder =
        CouponViewHolder(CouponViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val binding = holder.binding

        var cID : Int = cID!![position]
        var cName = cName!![position]

        binding.cName.text = cName

            binding.useButton.setOnClickListener {
            val intent = Intent(binding.root.context, QRActivity::class.java)
            intent.putExtra("couponID",cID)
                Log.d("cID", "QR액티비티로 날아갈 cID : $cID")
            intent.putExtra("couponName",cName)

            binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cName?.size ?: 0
    }
}
