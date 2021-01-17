package com.mbd.bankmanager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionsListAdapter extends  BaseAdapter {
    private ArrayList<ListItem> listData;
    private LayoutInflater layoutInflater;
    public TransactionsListAdapter(Context aContext, ArrayList<ListItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public TransactionsListAdapter(Context context, int textViewResourceId, ArrayList userList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
    }


    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.uTrasaction = (TextView) v.findViewById(R.id.amount);
            holder.uDate = (TextView) v.findViewById(R.id.date);
            holder.uTransactionType = (TextView) v.findViewById(R.id.transactionType);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }


        holder.uDate.setText(listData.get(position).getDate());
        holder.uTransactionType.setText(listData.get(position).getTransaction());
        holder.uTrasaction.setText(listData.get(position).getAmount());
        return v;
    }
    static class ViewHolder {
        TextView uTrasaction;
        TextView uDate;
        TextView uTransactionType;
    }



}
