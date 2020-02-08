package com.pluu.savedstate.ui

import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter

class MainDemoActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val path = intent.getStringExtra("com.example.android.apis.Path").orEmpty()
        listAdapter = SimpleAdapter(
            this,
            getData(path),
            R.layout.simple_list_item_1,
            arrayOf("title"),
            intArrayOf(R.id.text1)
        )
        listView.isTextFilterEnabled = true
    }

    private fun getData(prefix: String): List<Map<String, Any>> {
        val myData: MutableList<Map<String, Any>> = ArrayList()
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory("com.pluu.savedstate.SAMPLE_CODE")
        val pm = packageManager
        val list = pm.queryIntentActivities(mainIntent, 0) ?: return myData
        val prefixPath: Array<String>?
        var prefixWithSlash = prefix
        if (prefix == "") {
            prefixPath = null
        } else {
            prefixPath = prefix.split("/").dropLastWhile { it.isEmpty() }.toTypedArray()
            prefixWithSlash = "$prefix/"
        }
        val len = list.size
        val entries: MutableMap<String, Boolean> =
            HashMap()
        for (i in 0 until len) {
            val info = list[i]
            val labelSeq = info.loadLabel(pm)
            val label = labelSeq?.toString() ?: info.activityInfo.name
            if (prefixWithSlash.isEmpty() || label.startsWith(prefixWithSlash)) {
                val labelPath = label.split("/")
                    .dropLastWhile { it.isEmpty() }
                val nextLabel =
                    if (prefixPath == null) labelPath[0] else labelPath[prefixPath.size]
                if (prefixPath?.size ?: 0 == labelPath.size - 1) {
                    addItem(
                        myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name
                        )
                    )
                } else {
                    if (entries[nextLabel] == null) {
                        addItem(
                            myData, nextLabel, browseIntent(
                                if (prefix.isEmpty()) nextLabel else "$prefix/$nextLabel"
                            )
                        )
                        entries[nextLabel] = true
                    }
                }
            }
        }
        myData.sortBy {
            it["title"].toString()
        }
        return myData
    }

    private fun activityIntent(pkg: String, componentName: String) = Intent().apply {
        setClassName(pkg, componentName)
    }

    private fun browseIntent(path: String) = Intent().apply {
        setClass(this@MainDemoActivity, MainDemoActivity::class.java)
        putExtra("com.example.android.apis.Path", path)
    }

    private fun addItem(
        data: MutableList<Map<String, Any>>,
        name: String,
        intent: Intent
    ) {
        data.add(
            mapOf("title" to name, "intent" to intent)
        )
    }

    override fun onListItemClick(
        l: ListView,
        v: View?,
        position: Int,
        id: Long
    ) {
        val map = l.getItemAtPosition(position) as Map<String, Any>
        val intent = map["intent"] as Intent
        startActivity(intent)
    }
}
