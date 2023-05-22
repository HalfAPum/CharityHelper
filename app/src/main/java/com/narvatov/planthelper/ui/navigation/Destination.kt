package com.narvatov.planthelper.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.narvatov.planthelper.R

sealed class Destination(private val baseRoute: String) {

    open val params: List<String> = emptyList()

    val route: String by lazy {
        StringBuilder(baseRoute).run {
            params.forEach { append("{$it}") }
            this.toString()
        }
    }

}

class Toast(val message: String) : Destination("Toaster")

object Back : Destination("Back") {

    fun withParam(destination: Destination, inclusive: Boolean): BackWithParam {
        return BackWithParam(destination, inclusive)
    }
}

class BackWithParam(
    val back: Destination,
    val inclusive: Boolean
) : Destination("BackWithParam")

sealed class HeaderDestination(
    @StringRes
    val headerTextRes: Int,
    val headerText: String,
    val canGoBack: Boolean = true
) : Destination(headerText)

sealed class BottomNavigation(
    @StringRes
    headerTextRes: Int,
    headerText: String,
    @DrawableRes val icon: Int,
    @StringRes
    val text: Int,
): HeaderDestination(
    headerTextRes = headerTextRes,
    headerText = headerText,
    canGoBack = false
) {

    object Requests : BottomNavigation(
        headerTextRes = R.string.helprequests,
        headerText = "Help requests",
        icon = R.drawable.ic_help_request_24,
        text = R.string.requersst
    )

    object Proposals : BottomNavigation(
        headerTextRes = R.string.proposals,
        headerText = "Proposals",
        icon = R.drawable.ic_help_proposal_24,
        text = R.string.prorporp
    )

    object Notifications : BottomNavigation(
        headerTextRes = R.string.notificationss,
        headerText = "Notifications",
        icon = R.drawable.ic_round_notification_important_24,
        text = R.string.notftif
    )

    object Account : BottomNavigation(
        headerTextRes = R.string.account,
        headerText = "Account",
        icon = R.drawable.ic_round_account_circle_24,
        text = R.string.acctount
    )

}

object SignIn : HeaderDestination(R.string.signinn,"Sign In", canGoBack = false)

object SignUp : HeaderDestination(R.string.signups,"Sign Up")

object CreateProposal : HeaderDestination(R.string.createproposal,"Create proposal")

object ProposalDetails : HeaderDestination(R.string.proposaldetails,"Proposal details")
object HelpDetails : HeaderDestination(R.string.helpdetails,"Help details")

object Filter : HeaderDestination(R.string.searchfilter,"Search filter")
object FilterHelp : HeaderDestination(R.string.searchfilterhelp,"Search filter help")
object Transactions : HeaderDestination(R.string.evventtransacitns,"Event transactions")
object HelpTransactions : HeaderDestination(R.string.evventtransacitns,"Event transactions")
object CreateTransaction : HeaderDestination(R.string.createtransaction,"Create transaction")
object CreateHelpTransaction : HeaderDestination(R.string.createtransaction,"Create transaction")
object CreateHelp : HeaderDestination(R.string.createhelpevent,"Create help event")
object EditProposal : HeaderDestination(R.string.editproposal,"Edit proposal")
object EditHelp : HeaderDestination(R.string.edithelp,"Edit help")

val bottomNavigationItems = listOf(
    BottomNavigation.Requests,
    BottomNavigation.Proposals,
    BottomNavigation.Notifications,
    BottomNavigation.Account,
)