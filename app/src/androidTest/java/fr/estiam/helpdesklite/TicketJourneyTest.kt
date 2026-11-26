
package fr.estiam.helpdesklite

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.contrib.RecyclerViewActions
import fr.estiam.helpdesklite.ui.TicketAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class TicketJourneyTest {

    @get:Rule
    val fixture = TicketUiFixture()

    @Test
    fun createsLowPriorityTicket() {

        onView(withId(R.id.addTicket))
            .perform(click())

        onView(withId(R.id.titleInput))
            .perform(
                replaceText("Test ticket LOW"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.descriptionInput))
            .perform(
                replaceText("Description of the test ticket"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.priorityInput))
            .perform(click())

        onView(withText("LOW"))
            .perform(click())

        onView(withId(R.id.saveTicket))
            .perform(scrollTo(), click())

        fixture.awaitCatalogueRows(1)

        onView(withId(R.id.ticketList))
            .perform(
                RecyclerViewActions.scrollToPosition<TicketAdapter.Holder>(0)
            )

        onView(withId(R.id.ticketList))
            .check(matches(isDisplayed()))

        onView(withText("Test ticket LOW"))
            .check(matches(isDisplayed()))

        onView(withText("LOW"))
            .check(matches(isDisplayed()))
    }
}
