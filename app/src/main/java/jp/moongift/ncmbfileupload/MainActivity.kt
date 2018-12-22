package jp.moongift.ncmbfileupload

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.nifcloud.mbaas.core.NCMB
import kotlinx.android.synthetic.main.activity_main.view.*
import com.nifcloud.mbaas.core.NCMBAcl
import android.R.attr.data
import android.util.Log
import com.nifcloud.mbaas.core.NCMBFile



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NCMB.initialize(applicationContext, BuildConfig.APPLICATION_KEY, BuildConfig.CLIENT_KEY)
        var btn = findViewById<Button>(R.id.btnSave)
        btn.setOnClickListener {
            this.saveMemo()
        }
    }
    fun saveMemo() {
        var data = findViewById<TextView>(R.id.txtMemo).text.toString().toByteArray()
        var acl = NCMBAcl()
        acl.publicReadAccess = true
        acl.publicWriteAccess = true
        val file = NCMBFile("Memo.txt", data, acl)
        file.saveInBackground { e ->
            if (e != null) {
                Log.d("Error", "${e.localizedMessage}")
            } else {
                Log.d("Success", "File saved.")
            }
        }
    }
}
