package k.s.yarlykov.materialdesign.ui.lesson6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import k.s.yarlykov.materialdesign.R

class RVAdapter(private val source: List<Pair<Int, String>>, private val itemResourceId : Int)
    : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

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
        return source.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(source[position])    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv = itemView.findViewById<ImageView>(R.id.iv_rv_item)
        val tv = itemView.findViewById<TextView>(R.id.tv_rv_item)

        fun bind(content: Pair<Int, String>) {
            iv.setImageResource(content.first)
            tv.text = content.second
        }
    }
}