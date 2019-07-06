package fauzi.hilmy.submission.ui.content

import androidx.lifecycle.ViewModel
import fauzi.hilmy.submission.data.Dummy
import fauzi.hilmy.submission.data.Entity

class ContentViewModel : ViewModel() {
    fun movie(): MutableList<Entity> {
        return Dummy().getMovies()
    }

    fun tvShow(): MutableList<Entity> {
        return Dummy().getShow()
    }
}