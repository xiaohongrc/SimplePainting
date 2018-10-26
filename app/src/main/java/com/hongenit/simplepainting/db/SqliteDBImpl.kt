package com.hongenit.simplepainting.db

import android.content.Context

class SqliteDBImpl(context: Context) : ISqliteDB {
    val mDbHelper: DatabaseOpenHelper = DatabaseOpenHelper(context)
    val TAG = "SqliteDBImpl"




}