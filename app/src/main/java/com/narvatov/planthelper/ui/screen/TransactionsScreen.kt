package com.narvatov.planthelper.ui.screen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.remote.RequestStatus
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.main.PICK_PHOTO_CODE
import com.narvatov.planthelper.ui.navigation.*
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.TransactionViewModel
import com.narvatov.planthelper.utils.isInvalidUrl
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalTextApi::class)
@Composable
fun TransactionsScreen(
    viewModel: TransactionViewModel = getViewModel()
) {

    val transactions = viewModel.transactionFlow.collectAsState(null)
    val event = viewModel.eventFlow.collectAsState(null)

    if (transactions.value.isNullOrEmpty()) {
        Text(text = stringResource(R.string.notransactionyet), fontSize = 20.sp)
    }

    Box(Modifier.fillMaxSize()) {
        if (transactions.value.isNullOrEmpty().not()) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            ListSpacer()

            items(transactions.value!!) { proposal -> with(proposal) {
                Card(
                    modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
                        .shadow(elevation = 10.dp, shape = Shapes.medium).clip(Shapes.medium)
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Text(
                            text = transactionStatus.toStringStatus(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = stringResource(R.string.creator),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                        Row(Modifier.padding(top = 8.dp)) {
                            AsyncImage(
                                model = creator.profileImageURL,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(48.dp)
                            )

                            Column(
                                Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = creator.username,
                                    style = MaterialTheme.typography.body2,
                                )

                                Text(
                                    text = creator.phone,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(top = 4.dp),
                                )
                            }
                        }

                        Text(
                            text = comment,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(vertical = 8.dp),
                        )

                        Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(color = Color.Gray))

                        Text(
                            text = stringResource(R.string.responder),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Row(Modifier.padding(top = 8.dp)) {
                            AsyncImage(
                                model = responder.profileImageURL,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(48.dp)
                            )

                            Column(
                                Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = responder.username,
                                    style = MaterialTheme.typography.body2,
                                )

                                Text(
                                    text = responder.phone,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(top = 4.dp),
                                )
                            }
                        }

                        if (reportURL.isNullOrBlank().not()) {
                            val uriHandler = LocalUriHandler.current
                            val annotatedLink =AnnotatedString.Builder().apply {
                                append(stringResource(R.string.report))
                                append(" ")
                                append(reportURL!!)
                                addStyle(
                                    style = SpanStyle(
                                        color = Color(0xff64B5F6),
                                        fontSize = 18.sp,
                                        textDecoration = TextDecoration.Underline
                                    ), start = stringResource(R.string.report).length + 1, end = stringResource(R.string.report).length + 1 + reportURL!!.length
                                )
                                addStringAnnotation(
                                    tag = "URL",
                                    annotation = reportURL!!,
                                    stringResource(R.string.report).length + 1,stringResource(R.string.report).length + 1 + reportURL!!.length
                                )
                            }.toAnnotatedString()
                            ClickableText(
                                text = annotatedLink,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.padding(top = 8.dp),
                                onClick = {
                                    annotatedLink.getStringAnnotations("URL", it, it)
                                        .firstOrNull()?.let { stringAnnotation ->
                                            uriHandler.openUri(stringAnnotation.item)
                                        }
                                }
                            )
                        }

                        if (responder.id == LoginStateHolder.signInState.signInData!!.id && transactionStatus == RequestStatus.Waiting.nameR) {
                            Row(Modifier.padding(top = 12.dp)) {
                                Button(
                                    onClick = {
                                        viewModel.accept(id)
                                        popBack()
                                        navigate(Transactions)
                                    },
                                    modifier = Modifier.weight(1F).padding(end = 4.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.confirmrequest),
                                    )
                                }

                                Button(
                                    onClick = {
                                        viewModel.reject(id)
                                        popBack()
                                        navigate(Transactions)
                                    },
                                    modifier = Modifier.weight(1F).padding(start = 4.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.declinereq)
                                    )
                                }
                            }
                        }

//                        if (creator.id == LoginStateHolder.signInState.signInData!!.id && transactionStatus == RequestStatus.Waiting.nameR) {
//                            Row(Modifier.padding(top = 12.dp)) {
//                                Button(
//                                    onClick = {
//                                        viewModel.updateTransactionStatus(id, RequestStatus.Aborted)
//                                        popBack()
//                                        navigate(Transactions)
//                                    },
//                                    modifier = Modifier.weight(1F).padding(end = 4.dp)
//                                ) {
//                                    Text(
//                                        text = stringResource(R.string.abort),
//                                    )
//                                }
//                            }
//                        }

                        if (responder.id == LoginStateHolder.signInState.signInData!!.id && transactionStatus == RequestStatus.Accepted.nameR) {
                            Row(Modifier.padding(top = 12.dp)) {
                                Button(
                                    onClick = {
                                        viewModel.updateTransactionStatus(id, RequestStatus.InProgress)
                                        popBack()
                                        navigate(Transactions)
                                    },
                                    modifier = Modifier.weight(1F).padding(end = 4.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.setinprogress),
                                    )
                                }

                                Button(
                                    onClick = {
                                        viewModel.updateTransactionStatus(id, RequestStatus.Aborted)
                                        popBack()
                                        navigate(Transactions)
                                    },
                                    modifier = Modifier.weight(1F).padding(start = 4.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.abort)
                                    )
                                }
                            }
                        }

//                        if (creator.id == LoginStateHolder.signInState.signInData!!.id && transactionStatus == RequestStatus.Accepted.nameR) {
//                            Row(Modifier.padding(top = 12.dp)) {
//                                Button(
//                                    onClick = {
//                                        viewModel.updateTransactionStatus(id, RequestStatus.Aborted)
//                                        popBack()
//                                        navigate(Transactions)
//                                    },
//                                    modifier = Modifier.fillMaxWidth()
//                                ) {
//                                    Text(
//                                        text = stringResource(R.string.abort)
//                                    )
//                                }
//                            }
//                        }

                        if (responder.id == LoginStateHolder.signInState.signInData!!.id && transactionStatus == RequestStatus.InProgress.nameR) {
                            Column {
                                val activity = LocalContext.current as Activity
                                Button(
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_GET_CONTENT)
                                        intent.type = "*/*"
                                        activity.startActivityForResult(intent, PICK_PHOTO_CODE)
                                    },
                                    modifier = Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally)
                                ) {
                                    Text(
                                        text = stringResource(R.string.selectDocument),
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )
                                }

                                val error = viewModel.errorSharedFlow.collectAsState(null)

                                val errormsg = stringResource(R.string.enter_valid_url)
                                Row(Modifier.padding(top = 12.dp)) {
                                    Button(
                                        onClick = {
                                                viewModel.updateTransactionStatus(
                                                    id,
                                                    RequestStatus.Completed
                                                )
                                                popBack()
                                                navigate(Transactions)
                                        },
                                        modifier = Modifier.weight(1F).padding(end = 4.dp)
                                    ) {
                                        Text(
                                            text = stringResource(R.string.complete),
                                        )
                                    }

                                    Button(
                                        onClick = {
                                            viewModel.updateTransactionStatus(
                                                id,
                                                RequestStatus.Aborted
                                            )
                                            popBack()
                                            navigate(Transactions)
                                        },
                                        modifier = Modifier.weight(1F).padding(start = 4.dp)
                                    ) {
                                        Text(
                                            text = stringResource(R.string.abort)
                                        )
                                    }
                                }

                                if (error.value != null) {
                                    Text(
                                        text = error.value!!,
                                        color = Color.Red,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                }

                            }
                        }
                    }

                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (responder.id == LoginStateHolder.signInState.signInData!!.id && false) {
                            FloatingActionButton(
                                onClick = { navigate(HelpComplaint) },
                                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            } }

            ListSpacer()
        }}

        if (event.value?.author?.id != LoginStateHolder.signInState.signInData?.id) {
            FloatingActionButton(
                onClick = { navigate(CreateTransaction) },
                modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun String.toStringStatus(): String {
    return when (this) {
        RequestStatus.Waiting.nameR -> stringResource(R.string.waitingstatus)
        RequestStatus.WaitingForApprove.nameR -> stringResource(R.string.advanceapprow)
        RequestStatus.Accepted.nameR -> stringResource(R.string.accepted)
        RequestStatus.InProgress.nameR -> stringResource(R.string.inprogress)
        RequestStatus.Completed.nameR -> stringResource(R.string.Completed)
        RequestStatus.Aborted.nameR -> stringResource(R.string.abortd)
        else -> stringResource(R.string.cancelled)
    }
}