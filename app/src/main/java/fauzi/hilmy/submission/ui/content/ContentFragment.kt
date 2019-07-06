package fauzi.hilmy.submission.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fauzi.hilmy.submission.R
import fauzi.hilmy.submission.data.Entity
import kotlinx.android.synthetic.main.fragment_content.*

public class ContentFragment : Fragment() {

    var isMovie = true
    private var contentAdapter: ContentAdapter? = null
    private var viewModel: ContentViewModel? = null
    private var content: MutableList<Entity>? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            isMovie = it.getBoolean(IS_MOVIE)
        }

    }

    companion object {
        const val IS_MOVIE = "isMovie"

        @JvmStatic
        fun newInstance(isMovie: Boolean) =
                ContentFragment().apply {
                    arguments = Bundle().apply {
                        putBoolean(IS_MOVIE, isMovie)
                    }
                }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            title.text = if (isMovie) "Movie" else "TV Show"
            viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)

            content = if (isMovie) viewModel!!.movie() else viewModel!!.tvShow()
            contentAdapter = ContentAdapter(content!!, activity!!, isMovie)
            with(rv_content) {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = contentAdapter

            }
        }
    }
}
