package fauzi.hilmy.submission.ui.content

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fauzi.hilmy.submission.R
import fauzi.hilmy.submission.data.Entity
import fauzi.hilmy.submission.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_content.view.*

public class ContentAdapter(
        private val content: MutableList<Entity>,
        private val activity: Context,
        private val isMovie: Boolean
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_content, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        holder.bind(content[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ID_DATA, content[position].id)
            intent.putExtra(DetailActivity.IS_MOVIE, isMovie)
            activity.startActivity(intent)
        }
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(dataa: Entity){
            with(v){
                txtJudul.text = dataa.name
                txtGenre.text = dataa.genre
                txt_rating.text = dataa.rating.toString()
                Glide.with(v)
                        .load(dataa.poster)
                        .apply(RequestOptions.fitCenterTransform())
                        .into(img_poster)
            }
        }
    }
}