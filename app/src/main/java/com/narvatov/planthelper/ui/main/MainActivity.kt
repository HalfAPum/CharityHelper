package com.narvatov.planthelper.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.navigation.compose.rememberNavController
import com.android.billingclient.api.*
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.ui.Scaffold
import com.narvatov.planthelper.ui.main.delegate.exception.CoroutineExceptionCollector
import com.narvatov.planthelper.ui.main.delegate.exception.ICoroutineExceptionCollector
import com.narvatov.planthelper.ui.main.delegate.firebase.FirebaseDelegate
import com.narvatov.planthelper.ui.main.delegate.firebase.IFirebaseDelegate
import com.narvatov.planthelper.ui.main.delegate.permission.IPermissionProvider
import com.narvatov.planthelper.ui.main.delegate.permission.PermissionProvider
import com.narvatov.planthelper.ui.main.delegate.syncer.ITaskStatusSyncer
import com.narvatov.planthelper.ui.main.delegate.syncer.TaskStatusSyncer
import com.narvatov.planthelper.ui.navigation.BottomBar
import com.narvatov.planthelper.ui.navigation.NavHostContent
import com.narvatov.planthelper.ui.theme.PlanthelperTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity(),
    IFirebaseDelegate by FirebaseDelegate,
    IPermissionProvider by PermissionProvider,
    ICoroutineExceptionCollector by CoroutineExceptionCollector,
    ITaskStatusSyncer by TaskStatusSyncer {

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                for (purchase in purchases) {
                    println("FUCK OKKKK FOR $purchase")
                }
            } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
                println("FUCK ERRR $billingResult")
            } else {
                // Handle any other error codes.
            }
            println("FUCK WTFF $billingResult")

        }

    private val billingClient by lazy { BillingClient . newBuilder (applicationContext)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()
    }

    var productDetails: ProductDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        suspend fun processPurchases() {
            val product = QueryProductDetailsParams.Product.newBuilder().run {
                setProductId("plant_4_slots_subscriptions")
                setProductType(BillingClient.ProductType.SUBS)
                build()
            }
            val productList = listOf(product)

            val params = QueryProductDetailsParams.newBuilder()
            params.setProductList(productList)

            // leverage queryProductDetails Kotlin extension function
            val productDetailsResult = withContext(Dispatchers.IO) {
                billingClient.queryProductDetails(params.build())
            }

            productDetails = productDetailsResult.productDetailsList?.firstOrNull()
            println("FUCK ME WTF ${productDetailsResult.productDetailsList}")
            // Process the result.
        }

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                println("FUCK CONNECTION FINISHED ${billingResult.responseCode}")
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    launchCatching {
                        processPurchases()
                    }
                    // The BillingClient is ready. You can query purchases here.
                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })


        initFirebase()
        checkNotificationPermission()
        initCoroutineExceptionCollector()
        syncFailedTasks()

        setContent {
            PlanthelperTheme {
                Button(
                    onClick = {
                        val productDetailsParamsList = listOf(
                            BillingFlowParams.ProductDetailsParams.newBuilder()
                                .setProductDetails(productDetails!!)
                                // to get an offer token, call ProductDetails.subscriptionOfferDetails()
                                // for a list of offers that are available to the user
                                .setOfferToken(productDetails!!.subscriptionOfferDetails!!.first().offerToken)
                                .build()
                        )

                        val billingFlowParams = BillingFlowParams.newBuilder()
                            .setProductDetailsParamsList(productDetailsParamsList)
                            .build()

// Launch the billing flow
                        val billingResult = billingClient.launchBillingFlow(this, billingFlowParams)
                    }
                ) {
                    Text(text = "FUCK ME")
                }
//                val navController = rememberNavController()
//                Scaffold(
//                    navController = navController,
//                    bottomBar = { BottomBar(navController) },
//                    content = { _, innerPadding -> NavHostContent(navController, innerPadding) },
//                )
            }
        }
    }
}