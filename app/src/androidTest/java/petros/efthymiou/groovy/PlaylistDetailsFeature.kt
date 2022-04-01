package petros.efthymiou.groovy

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistDetailsFeature : BaseUiTest() {

    val mActivityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displaysPlaylistNameAndDetails() {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.playlist_image), ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.playlists_list), 0))))
            .perform(ViewActions.click())
        BaristaVisibilityAssertions.assertDisplayed("Hard Rock Cafe")
    }

}