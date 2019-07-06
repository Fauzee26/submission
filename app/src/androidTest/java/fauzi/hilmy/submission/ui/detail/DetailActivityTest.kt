package fauzi.hilmy.submission.ui.detail


import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import fauzi.hilmy.submission.R
import fauzi.hilmy.submission.utils.FakeDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {
    private val movie = FakeDummy().getMovies()[0]

    @get:Rule
    var activityRule: ActivityTestRule<DetailActivity> = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.ID_DATA, movie.id)
            return result
        }
    }

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.judul)).check(matches(isDisplayed()))
        onView(withId(R.id.judul)).check(matches(withText(movie.name)))
        onView(withId(R.id.txtrating)).check(matches(isDisplayed()))
        onView(withId(R.id.txtrating)).check(matches(withText(movie.rating.toString())))
    }
}