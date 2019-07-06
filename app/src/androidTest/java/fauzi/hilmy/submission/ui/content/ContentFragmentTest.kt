package fauzi.hilmy.submission.ui.content


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import fauzi.hilmy.submission.R
import fauzi.hilmy.submission.testing.SingleFragmentActivity
import fauzi.hilmy.submission.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContentFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val contentFragment = ContentFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(contentFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadCourses() {
        onView(withId(R.id.rv_content)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_content)).check(RecyclerViewItemCountAssertion(10))
    }
}