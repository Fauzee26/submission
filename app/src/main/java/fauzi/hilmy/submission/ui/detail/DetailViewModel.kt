package fauzi.hilmy.submission.ui.detail

import androidx.lifecycle.ViewModel
import fauzi.hilmy.submission.data.Dummy
import fauzi.hilmy.submission.data.Entity

class DetailViewModel : ViewModel() {

    private lateinit var entity: Entity
    private lateinit var id: String

    fun detail(isMovie: Boolean): Entity {
        val contentEntity = if (isMovie) Dummy().getMovies() else Dummy().getShow()
        for (i in 0 until contentEntity.size) {
            if (id == contentEntity[i].id) {
                entity = contentEntity[i]
            }
        }
        return entity
    }

    fun setId(contentId: String) {
        this.id = contentId
    }
}