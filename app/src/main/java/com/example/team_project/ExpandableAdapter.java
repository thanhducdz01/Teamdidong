package com.example.team_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<GroupObject> groupObjects;
    private Map<GroupObject,List<ItemObject>> listMap;

    public ExpandableAdapter(List<GroupObject> groupObjects, Map<GroupObject, List<ItemObject>> listMap) {
        this.groupObjects = groupObjects;
        this.listMap = listMap;
    }

    @Override
    public int getGroupCount() {
        if(groupObjects!= null)
            return groupObjects.size();
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        if(groupObjects!=null && listMap != null)
            return listMap.get(groupObjects.get(i)).size();
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return groupObjects.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listMap.get(groupObjects.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        GroupObject object  = groupObjects.get(i);
        return object.getId();
    }

    @Override
    public long getChildId(int i, int i1) {

        ItemObject object = listMap.get(groupObjects.get(i)).get(i1);
        return object.getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group, viewGroup, false);
        }
        ImageView imageView_left = view.findViewById(R.id.img_left);
        TextView textView = view.findViewById(R.id.txtView);
        GroupObject object = groupObjects.get(i);

        ImageView img_indiacator = view.findViewById(R.id.ivGroupIndicator);
        img_indiacator.setSelected(b);


        imageView_left.setImageResource(object.getImg_left());
        textView.setText(object.getTitle());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        }
        TextView textView_title = view.findViewById(R.id.item_title);
        TextView textView_note = view.findViewById(R.id.item_note);

        ItemObject object = listMap.get(groupObjects.get(i)).get(i1);

        textView_title.setText(object.getTitle());
        textView_note.setText(object.getNote());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
