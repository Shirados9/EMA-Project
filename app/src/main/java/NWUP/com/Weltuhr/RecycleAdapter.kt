package NWUP.com.Weltuhr

import NWUP.com.R
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val photos: ArrayList<ContactsContract.Contacts.Photo>) : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PhotoHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = photos.size




    class PhotoHolder(v:View) : RecyclerView.ViewHolder(v),View.OnClickListener{
        private var view: View = v
        private var photo: ContactsContract.Contacts.Photo? = null

        init {
            v.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            Log.d("RecyclerView", "Click!")
        }

        companion object{
            private val PHOTO_KEY = "PHOTO"
        }
    }
}