package fauzi.hilmy.submission.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import fauzi.hilmy.submission.R
import fauzi.hilmy.submission.data.Entity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){
    private var isMovie = true
    private lateinit var viewModel: DetailViewModel
    var path = "https://www.youtube.com/watch?v="

    companion object {
        const val ID_DATA = "id"
        const val IS_MOVIE = "isMovie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        val id = intent.getStringExtra(ID_DATA)
        isMovie = intent.getBooleanExtra(IS_MOVIE, true)
        titlee.text = if (isMovie) "Detail Movie" else "Detail TV Show"
        viewModel.setId(id)
        populate(viewModel.detail(isMovie))

        back_button.setOnClickListener { finish() }

    }

    private fun populate(detail: Entity) {
        with(detail){
            judul.text = name
            yearr.text = year
            genree.text = genre
            durationn.text = duration
            txt_desc.text = desc
            txtrating.text = rating.toString()

            Glide.with(applicationContext)
                    .load(poster)
                    .into(img_posterr)

            Glide.with(applicationContext)
                    .load("https://img.youtube.com/vi/$trailer/maxresdefault.jpg")
                    .into(img_thumbnaill)

            img_thumbnaill.setOnClickListener {
                val video_path = path + trailer
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video_path))
                startActivity(intent)
            }

            share_button.setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                        .from(this@DetailActivity)
                        .setType(mimeType)
                        .setChooserTitle("Bagikan info ini sekarang.")
                        .setText(String.format("Segera tonton film %s pada %s di bioskop kesayangan anda", name, year))
                        .startChooser()
            }
        }
    }
}
