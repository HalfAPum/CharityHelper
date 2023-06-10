package com.narvatov.planthelper.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.remote.help.HelpDetails
import com.narvatov.planthelper.models.remote.proposal.EditHelp
import com.narvatov.planthelper.models.remote.proposal.EditProposal
import com.narvatov.planthelper.models.remote.proposal.ProposalDetails
import com.narvatov.planthelper.ui.screen.CreateTransactionScreen
import com.narvatov.planthelper.ui.screen.FilterHelpScreen
import com.narvatov.planthelper.ui.screen.FilterScreen
import com.narvatov.planthelper.ui.screen.TransactionsScreen
import com.narvatov.planthelper.ui.screen.accont.Account
import com.narvatov.planthelper.ui.screen.help.CreateHelp
import com.narvatov.planthelper.ui.screen.help.HelpComplaint
import com.narvatov.planthelper.ui.screen.proposal.ProposalComplaint
import com.narvatov.planthelper.ui.screen.help.CreateHelpTransactionScreen
import com.narvatov.planthelper.ui.screen.help.HelpList
import com.narvatov.planthelper.ui.screen.help.HelpTransactionsScreen
import com.narvatov.planthelper.ui.screen.notification.NotificationList
import com.narvatov.planthelper.ui.screen.proposal.CreateProposal
import com.narvatov.planthelper.ui.screen.proposal.ProposalList
import com.narvatov.planthelper.ui.screen.signs.SignIn
import com.narvatov.planthelper.ui.screen.signs.SignUpScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun NavHostContent(
    navController: NavHostController,
    innerPadding: PaddingValues
) = with(navController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = BottomNavigation.Requests,
        modifier = Modifier.padding(innerPadding),
    ) {
        scope.launchCatching {
            UiNavigator.navigationEvents.collectLatest { destination ->
                when(destination) {
                    is BackWithParam -> {
                        val poppedBackSuccessfully = popBackStack(
                            destination = destination.back,
                            inclusive = destination.inclusive,
                        )

                        if (poppedBackSuccessfully) return@collectLatest

                        popBackStack(
                            destination = BottomNavigation.Requests,
                            inclusive = false,
                        )
                    }
                    is Toast -> {
                        android.widget.Toast.makeText(
                            context, destination.message,
                            android.widget.Toast.LENGTH_LONG
                        ).show()
                    }
                    Back -> popBackStack()
                    else -> {
                        val poppedSuccessfully = popBackStack(
                            destination = destination,
                            inclusive = false,
                        )

                        if (poppedSuccessfully) return@collectLatest

                        navigate(destination)
                    }
                }

            }
        }

        bottomNavigation {
            composable(BottomNavigation.Requests) {
                HelpList()
            }

            composable(BottomNavigation.Proposals) {
                ProposalList()
            }

            composable(BottomNavigation.Notifications) {
                NotificationList()
            }

            composable(BottomNavigation.Account) {
                if (LoginStateHolder.signInState.isLoggedIn) Account()
                else navigate(SignIn)
            }
        }

        composable(SignIn) {
            SignIn()
        }

        composable(SignUp) {
            SignUpScreen()
        }

        composable(CreateProposal) {
            CreateProposal()
        }

        composable(ProposalDetails) {
            ProposalDetails()
        }

        composable(HelpDetails) {
            HelpDetails()
        }

        composable(Filter) {
            val viewModelStateOwner = remember {
                navController.getBackStackEntry(BottomNavigation.Proposals)
            }

            FilterScreen(viewModel = getViewModel(owner = viewModelStateOwner))
        }

        composable(FilterHelp) {
            val viewModelStateOwner = remember {
                navController.getBackStackEntry(BottomNavigation.Requests)
            }

            FilterHelpScreen(viewModel = getViewModel(owner = viewModelStateOwner))
        }

        composable(Transactions) {
            TransactionsScreen()
        }
        composable(CreateTransaction) {
            CreateTransactionScreen()
        }

        composable(HelpTransactions) {
            HelpTransactionsScreen()
        }
        composable(CreateHelpTransaction) {
            CreateHelpTransactionScreen()
        }

        composable(CreateHelp) {
            CreateHelp()
        }

        composable(EditProposal) {
            EditProposal()
        }

        composable(EditHelp) {
            EditHelp()
        }

        composable(HelpComplaint) {
            HelpComplaint()
        }

        composable(ProposalComplaint) {
            ProposalComplaint()
        }
    }
}