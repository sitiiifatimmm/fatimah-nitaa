package com.example.fatimahnita

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseSuratIzin(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_USER = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        private const val TABLE_PERMIT= "surat_izin"
        private const val COLUMN_PERMIT_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_NOTE= "note"
        private const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUserTable = """
            CREATE TABLE $TABLE_USER (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createUserTable)

        // Sample data
        val sampleInsert = """
            INSERT INTO $TABLE_USER ($COLUMN_USERNAME, $COLUMN_PASSWORD)
            VALUES ('admin', '123'), ('user', 'password')
        """
        db.execSQL(sampleInsert)

        val createPermitTable = """
        CREATE TABLE $TABLE_PERMIT(
            $COLUMN_PERMIT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT NOT NULL,
            $COLUMN_NOTE TEXT,
            $COLUMN_DATE DATE
        )
        
        
        
        
    """.trimIndent()
        db.execSQL(createPermitTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PERMIT")
        onCreate(db)
    }

    // Fungsi untuk menyimpan (menambahkan) user baru
    fun insertUser(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }

        val result = db.insert(TABLE_USER, null, values)
        db.close()
        return result != -1L // return true jika insert berhasil
    }

    // Fungsi untuk menghapus user berdasarkan username
    fun deleteUser(username: String): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_USER, "$COLUMN_USERNAME = ?", arrayOf(username))
        db.close()
        return result > 0 // return true jika ada baris yang dihapus
    }


    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun insertPermit(
        name: String,
        date: String,
        note: String,

    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_NOTE, note)
            put(COLUMN_DATE, date)

        }
        val result = db.insert(TABLE_PERMIT, null, values)
        db.close()
        return result != -1L
    }

    fun getAllPermit(): List<Map<String, String>> {
        val db = this.readableDatabase
        val permitList = mutableListOf<Map<String, String>>()

        val cursor = db.rawQuery("SELECT * FROM $TABLE_PERMIT", null)
        if (cursor.moveToFirst()) {
            do {
                val permit = mapOf(
                    COLUMN_PERMIT_ID to cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_PERMIT_ID
                        )
                    ).toString(),
                    COLUMN_NAME to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    COLUMN_NOTE to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)),
                    COLUMN_DATE to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                    )
               permitList.add(permit)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return permitList
    }

    fun updatePermit(
        id: Int,
        name: String,
        note: String,
        date: String,

    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_NOTE, note)
            put(COLUMN_DATE, date)
        }

        val result =
            db.update(TABLE_PERMIT, values, "$COLUMN_PERMIT_ID = ?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun deletePermit(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_PERMIT, "$COLUMN_PERMIT_ID = ?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun getPermitById(customerId: Int): Map<String, String> {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_PERMIT,
            null,
            "$COLUMN_PERMIT_ID = ?",
            arrayOf(customerId.toString()),
            null, null, null
        )

        val customer = mutableMapOf<String, String>()

        if (cursor.moveToFirst()) {
            customer["id"] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PERMIT_ID))
            customer["name"] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            customer["dob"] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE))
            customer["phone"] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))

        }

        cursor.close()
        db.close()
        return customer
    }

}