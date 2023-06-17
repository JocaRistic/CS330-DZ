package rs.ac.metropolitan.ispitizprogramiranja

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBAdapter(val context: Context) {
    private var DBHelper: DatabaseHelper = DatabaseHelper(context)
    var db: SQLiteDatabase? = null

    private class DatabaseHelper internal constructor(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            try {
                db.execSQL(DATABASE_CREATE)
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.w(
                TAG, "Ažuriranje verzije baze podataka sa " + oldVersion + " na verziju "
                        + newVersion + ", a to će uništiti postojeće podatke"
            )
            db.execSQL("DROP TABLE IF EXISTS student")
            onCreate(db)
        }
    }

    //---otvaranje baze podataka---
    @Throws(SQLException::class)
    fun open(): DBAdapter {
        db = DBHelper.writableDatabase
        return this
    }

    //---zatvaranje baze podataka---
    fun close() {
        DBHelper.close()
    }

    //---umetanje studenta u bazu---
    fun insertStudent(brojIndeksa: Int, ime: String?, brojBodova: Double?): Long {
        val initialValues = ContentValues()
        initialValues.put(KEY_BROJ_INDEKSA, brojIndeksa)
        initialValues.put(KEY_IME, ime)
        initialValues.put(KEY_BROJ_BODOVA, brojBodova)
        return db!!.insert(DATABASE_TABLE, null, initialValues)
    }

    //---brisanje konkretnog studenta---
    fun deleteStudent(brojIndeksa: Int): Boolean {
        return db!!.delete(DATABASE_TABLE, KEY_BROJ_INDEKSA + "=" + brojIndeksa, null) > 0
    }

    //---preuzima sve studente---
    val allStudents: Cursor
        get() = db!!.query(
            DATABASE_TABLE, arrayOf(
                KEY_BROJ_INDEKSA, KEY_IME,
                KEY_BROJ_BODOVA
            ), null, null, null, null, null
        )

    //---preuzima konkretnog studenta---
    @Throws(SQLException::class)
    fun getStudent(brojIndeksa: Int): Cursor? {
        val mCursor = db!!.query(
            true, DATABASE_TABLE, arrayOf(
                KEY_BROJ_INDEKSA,
                KEY_IME, KEY_BROJ_BODOVA
            ), KEY_BROJ_INDEKSA + "=" + brojIndeksa, null,
            null, null, null, null
        )
        mCursor?.moveToFirst()
        return mCursor
    }

    //---ažurira studenta---
    fun updateStudent(brojIndeksa: Int, ime: String?, brojBodova: Double?): Boolean {
        val args = ContentValues()
        args.put(KEY_IME, ime)
        args.put(KEY_BROJ_BODOVA, brojBodova)
        return db!!.update(DATABASE_TABLE, args, KEY_BROJ_INDEKSA + "=" + brojIndeksa, null) > 0
    }

    companion object {
        const val KEY_BROJ_INDEKSA = "broj_indeksa"
        const val KEY_IME = "ime"
        const val KEY_BROJ_BODOVA = "broj_bodova"
        const val TAG = "DBAdapter"
        const val DATABASE_NAME = "ispit"
        const val DATABASE_TABLE = "student"
        const val DATABASE_VERSION = 2
        const val DATABASE_CREATE =
            ("create table student (broj_indeksa integer primary key autoincrement, "
                    + "ime text not null, broj_bodova numeric not null);")
    }

}