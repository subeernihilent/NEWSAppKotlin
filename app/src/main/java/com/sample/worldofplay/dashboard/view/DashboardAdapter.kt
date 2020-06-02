package com.sample.worldofplay.dashboard.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.worldofplay.R
import com.sample.worldofplay.dashboard.model.Stories


class DashboardAdapter(private val context: Context, private val storiesList: ArrayList<Stories>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storiesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = storiesList[position].title
        holder.itemLayout?.setOnClickListener {
            launchDetailScreen(storiesList[position].title, storiesList[position].url)
        }
    }

    private fun launchDetailScreen(title: String?, url: String?) {
        val intent = Intent(context, DashboardDetailActivity::class.java);
        intent.putExtra("title", title)
        intent.putExtra("url", url)
        context.startActivity(intent);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView? = itemView.findViewById(R.id.title_textview)
        val itemLayout: View? = itemView.findViewById(R.id.item_layout)
    }
}