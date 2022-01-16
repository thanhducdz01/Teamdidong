package com.example.team_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CTLHP_Adapter extends BaseAdapter implements Filterable {

    private Context context;
    private int layout;
    private List<CTLHP>  ctlhpList;
    private List<CTLHP>  ctlhpListOld;

    public CTLHP_Adapter(Context context, int layout, List<CTLHP> ctlhpList) {
        this.context = context;
        this.layout = layout;
        this.ctlhpList = ctlhpList;
        this.ctlhpListOld = ctlhpList;
    }

    @Override
    public int getCount() {
        return ctlhpList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private  class ViewHolder{
        TextView tenMH, stc;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);

            // anh xa
            holder.tenMH = (TextView) view.findViewById(R.id.item_tenMH);
            holder.stc   = (TextView) view.findViewById(R.id.item_stc);

            // gan gia tri
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        CTLHP ctlhp = ctlhpList.get(i);
        holder.tenMH.setText(ctlhp.getTenMH());
        holder.stc.setText("Số tín chỉ: "+ctlhp.getStc());
        return view;
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()){
                    ctlhpList = ctlhpListOld;
                }else {
                    List<CTLHP> list = new ArrayList<>();
                    for (CTLHP ctlhp: ctlhpListOld){
                        if (ctlhp.getTenMH().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(ctlhp);
                        }

                    }
                    ctlhpList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = ctlhpList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ctlhpList= (List<CTLHP>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}