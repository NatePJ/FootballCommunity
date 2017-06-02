package com.bp.footballcommunity;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by BP on 2017/5/31.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public static final int TITLE=0;
    public static final int IMAGE=1;
    public static final int BODY=2;
    public static final int BOTTOM=3;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    /**
     * ViewHolder
     */
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;
        public TextView titleTextView;
        private Context context;

        public ViewHolder(final View itemView,int itemType){
            super(itemView);

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            switch (itemType){
                case IMAGE:
                    nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
                    messageButton = (Button)itemView.findViewById(R.id.message_button);
                    break;
                case TITLE:
                    titleTextView = (TextView)itemView.findViewById(R.id.id_num);
            }
            this.context = getContext();
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    // Triggers click upwards to the adapter on click
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(itemView,position);
                        }
                    }
                }
            });
        }

        //Handles the row being clicked
//        @Override
//        public void onClick(View view){
//            int position = getAdapterPosition();
//            if(position != RecyclerView.NO_POSITION){
////                User user = users.get(position);
//                Toast.makeText(context,nameTextView.getText(),Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    /**
     * Now that we've defined the basic adapter and ViewHolder
     */
    // Store a member variable for the contacts
    private List<Contact> mContacts;
    // Store the context for easy access
    private Context mContext;

    /**
     * 自定义构造函数，可以传递入我们需要的所有数据
     * @param context
     * @param contacts
     */
    // Pass in the contact array into the constructor
    public ContactsAdapter(Context context, List<Contact> contacts){
        mContacts = contacts;
        mContext = context;
    }
    // Easy access to the context object in the recyclerview
    private Context getContext(){
        return mContext;
    }

    /**
     * Every adapter has three primary methods:
     * onCreateViewHolder to inflate the item layout and create the holder,
     * onBindViewHolder to set the view attributes based on the data,
     * getItemCount to determine the number of items.
     * We need to implement all three to finish the adapter:
     */

    // 返回内部类ViewHolder
    // 内部类的调用方法Class.Class
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(context);

        switch(viewType){
            case IMAGE:
                View contactView1 = inflater.inflate(R.layout.item_person,parent,false);
                viewHolder = new ViewHolder(contactView1,viewType);
                break;
            case TITLE:
                View contactView2 = inflater.inflate(R.layout.item_rv,parent,false);
                viewHolder = new ViewHolder(contactView2,viewType);
                break;
        }
        // Inflate the custom layout
//        View contactView = inflater.inflate(R.layout.item_person,parent,false);

        // Return a new holder instance
//        ViewHolder viewHolder = new ViewHolder(context,contactView,viewType);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    //设置组件值
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position){
        // Get the data model based on position
        Contact contact = mContacts.get(position);

        switch(viewHolder.getItemViewType()){
            case IMAGE:
                TextView nameTextView = viewHolder.nameTextView;
                nameTextView.setText(contact.getName());
                Button messageButton = viewHolder.messageButton;
                messageButton.setText("Message");
                break;
            case TITLE:
                TextView titleTextView = viewHolder.titleTextView;
                titleTextView.setText(contact.getName());
        }
        // Set item views based on your views and data model

    }

    // Returns the total count of items in the list

    @Override
    public int getItemCount(){
        return mContacts.size();
    }

    //重写getItemViewType(int position)
    @Override
    public int getItemViewType(int position){
        return mContacts.get(position).getContactType();
    }

    /**
     * Next, you would implement a swapItems() method on your adapter to perform the diff
     * and then invoke dispatchUpdates() to notify the adapter whether the element was inserted,
     * removed, moved, or changed:
     * @param contacts
     */
    public void swapItems(List<Contact> contacts){
        //compute diffs
        final ContactDiffCallback diffCallback = new ContactDiffCallback(this.mContacts, contacts);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        //clear contacts and add
        this.mContacts.clear();
        this.mContacts.addAll(contacts);

        diffResult.dispatchUpdatesTo(this);// calls adapter's notify methods after diff is computed
    }
}
