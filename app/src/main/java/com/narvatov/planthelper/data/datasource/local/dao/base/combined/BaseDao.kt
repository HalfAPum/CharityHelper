package com.narvatov.planthelper.data.datasource.local.dao.base.combined

import com.narvatov.planthelper.data.datasource.local.dao.base.DeleteDao
import com.narvatov.planthelper.data.datasource.local.dao.base.InsertDao
import com.narvatov.planthelper.data.datasource.local.dao.base.UpdateDao

interface BaseDao<T> : InsertDao<T>, UpdateDao<T>, DeleteDao<T>