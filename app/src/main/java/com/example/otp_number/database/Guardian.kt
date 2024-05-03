package com.example.otp_number.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guardian_table")
data class Guardian (
    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    @ColumnInfo(name = "guardian_name")
    var guardianName: String = "",

    @ColumnInfo(name = "guardian_relation")
    var guardianRelation: String = "",

    @ColumnInfo(name = "guardian_phone")
    var guardianPhoneNo: String = "",

    @ColumnInfo(name = "guardian_emailed")
    var guardianEmail: String = ""
)

