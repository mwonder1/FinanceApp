package com.example.android.financeapp.data

import android.content.Context
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.financeapp.R


class CustomAdapter(context: Context, cursor: Cursor) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

     var mContext: Context
     var mCursor: Cursor

    init {
        mContext = context
        mCursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val v = LayoutInflater.from(parent.context)
        val view = v.inflate(R.layout.stock_view_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCursor.count
    }

    override fun onBindViewHolder(holder: CustomViewHolder, postion: Int) {

        if (!mCursor.moveToPosition(postion)) {
            return
        }

        val ticker = mCursor.getString(mCursor.getColumnIndex(StocksContract.StockEntry.COLUMN_TICKER))
        val shares = mCursor.getString(mCursor.getColumnIndex(StocksContract.StockEntry.COLUMN_SHARE))
        val cost = mCursor.getString(mCursor.getColumnIndex(StocksContract.StockEntry.COLUMN_COST))

        holder.ticker.text = ticker
        holder.shares.text = shares
        holder.cost.text = cost
    }

    fun swapCursor(newCursor: Cursor){
        if(mCursor != null){
            mCursor.close()
        }
        mCursor = newCursor
        if(newCursor != null){
            notifyDataSetChanged()
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ticker: TextView
        var shares: TextView
        var cost: TextView

        init {
            super.itemView
            ticker = itemView.findViewById(R.id.stockViewTicker)
            shares = itemView.findViewById(R.id.stockViewShares)
            cost = itemView.findViewById(R.id.stockViewCost)
        }
    }
}
