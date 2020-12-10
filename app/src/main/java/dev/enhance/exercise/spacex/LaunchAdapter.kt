package dev.enhance.exercise.spacex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.enhance.exercise.R
import dev.enhance.exercise.core.repository.models.Launch
import java.text.SimpleDateFormat
import java.util.*

class LaunchAdapter(private val launchList: List<Launch>): RecyclerView.Adapter<LaunchAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val badgeIv: ImageView = view.findViewById(R.id.iv_badge)
        val nameTv: TextView = view.findViewById(R.id.tv_name)
        val dateTv: TextView = view.findViewById(R.id.tv_date)
        val statusIv: ImageView = view.findViewById(R.id.iv_status)

        fun setContent(launch: Launch) {
            Picasso.get().load(launch.links.patch.small).placeholder(R.drawable.ic_download).into(badgeIv)

            nameTv.text = launch.name

            dateTv.text = SimpleDateFormat("dd-MM-yyyy", Locale.US).format(launch.date_utc)

            if (launch.success == null) {
                statusIv.setImageResource(R.drawable.ic_scheduled)
            } else {
                statusIv.setImageResource(if (launch.success) R.drawable.ic_success else R.drawable.ic_error)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_launch, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) = holder.setContent(launchList[position])

    override fun getItemCount(): Int = launchList.size
}