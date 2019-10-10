package k.s.yarlykov.materialdesign.ui.lesson7


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import k.s.yarlykov.materialdesign.R
import kotlin.random.Random

class RVAdapterL7(private val pics: List<Int>, private val itemResourceId: Int) :
    RecyclerView.Adapter<RVAdapterL7.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                itemResourceId,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pics.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pics[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMain = itemView.findViewById<ImageView>(R.id.iv_rv_item_l7)
        val ivHeart = itemView.findViewById<ImageView>(R.id.iv_heart_l7)
        val ivStar = itemView.findViewById<ImageView>(R.id.iv_star_l7)
        val tvHeart = itemView.findViewById<TextView>(R.id.tv_hearts_l7)
        val tvStar = itemView.findViewById<TextView>(R.id.tv_stars_l7)

        fun bind(picId: Int) {
            ivMain.setImageResource(picId)

            var likes = getLike()
            if(likes > 0) {
                ivHeart.setImageResource(R.drawable.ic_heart_solid)
                tvHeart.text = likes.toString()
            }
            likes = getLike()
            if(likes > 0) {
                ivStar.setImageResource(R.drawable.ic_star)
                tvStar.text = likes.toString()
            }
        }
    }

    fun getLike() = Random.nextInt(0, 6)
}