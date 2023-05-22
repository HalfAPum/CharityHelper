package com.narvatov.planthelper.models.remote

enum class RequestStatus(val nameR: String) {
    Waiting("waiting"),
    WaitingForApprove("waiting_for_approve"),
    Accepted("accepted"),
    InProgress("in_progress"),
    Completed("completed"),
    Aborted("aborted"),
    Canceled("canceled"),
}