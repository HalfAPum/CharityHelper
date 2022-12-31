package com.narvatov.planthelper.models.data.local.task

enum class TaskStatus(val priority: Int) {
    Scheduled(10),
    Active(9),
    Failed(8),
    Completed(7),
}

fun TaskStatus.isAtMost(another: TaskStatus): Boolean {
    return priority <= another.priority
}

fun TaskStatus.isAtLeast(another: TaskStatus): Boolean {
    return priority >= another.priority
}

/*
Task status flow

Scheduled (Task is in work manager waiting for its time to show)
    |
    |
Active (Task was already shown to user but it is still active. It's time hasn't gone yet)
    |
    |
Failed (Task was not completed in time by user)
    |
    |
Completed (Task was completed neither in time or not)

 */