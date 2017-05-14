package t27.surreyfooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import t27.surreyfooddeliveryapp.LocalOrders.CachedOrderPrefrence;
import t27.surreyfooddeliveryapp.objectstodb.Customer;
import t27.surreyfooddeliveryapp.objectstodb.Order;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static t27.surreyfooddeliveryapp.LocalOrders.CachedOrderPrefrence.getOrderByEmail;

public class CurrentOrderActivity extends AppCompatActivity {
    private Intent Intent_get_it;
    SharedPreferences shar_pre;
    //ImageView icon_profile;
    String account_type = null;

    private ListView orderList;
    private DatabaseReference mDatabaseRef;

    private String cur_email;



    private OrderAdapter ordersAdapter;
    private ArrayList<Order> orders;
    private HashMap<DatabaseReference,ValueEventListener> mapOfRefToOrderListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        Intent_get_it = getIntent();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        //listener arrayList to detach listeners in onstop
        mapOfRefToOrderListener = new HashMap<DatabaseReference,ValueEventListener>();


        //icon_profile = (ImageView) findViewById(R.id.profile_icon);
        //get back the customer object
        shar_pre = getApplicationContext().getSharedPreferences(getString(R.string.User_info), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shar_pre.getString("userObject", null);

        //log in as customer or restaurant
        //or logged in before
        if (json != null) {
            Customer cur_customer = gson.fromJson(json, Customer.class);
            //get type
            account_type = cur_customer.getAccountType();
            //set button order text based on type(restaurant or customer)
/*            if (account_type.equals("customer")) {
                icon_profile.setImageResource(R.drawable.customericon);
            } else if (account_type.equals("restaurant")) {
                icon_profile.setImageResource(R.drawable.restauranticon);
            }*/
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        orderList = (ListView) findViewById(R.id.order_detail);
        cur_email = getApplicationContext().getSharedPreferences(getString(R.string.User_info), Context.MODE_PRIVATE)
                            .getString("curEmail",null);
        //if not logged in, it is a guest
        cur_email = (cur_email==null)?"guest":cur_email;
        orders = getOrderByEmail(getApplicationContext(),cur_email);

        ordersAdapter =
                new OrderAdapter(this,orders);
        //Log.d("numorder", "onStart: " + orders.size());
        // Set The Adapter
        orderList.setAdapter(ordersAdapter);
        //Log.d("prints", "onStart: "+ orders);

        //db references related to orders stored before
        for (Order oneorder :orders) {
            DatabaseReference ref = mDatabaseRef.child("order").child(oneorder.getOrderUid()).child("state");
            ValueEventListener valueListener = new OrderChangeListener(oneorder);
            //listening to data base for state changes
            ref.addValueEventListener(valueListener);

            //keep a copy to listener arrayList for detaching later in onstop
            mapOfRefToOrderListener.put(ref,valueListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (Map.Entry<DatabaseReference, ValueEventListener> entry : mapOfRefToOrderListener.entrySet())
        {
            entry.getKey().removeEventListener(entry.getValue());
        }
    }

    public void logo_click(View view) {
        Intent newIntent = new Intent(this, HomeActivity.class);
        startActivity(newIntent);
    }

    @Override
    public void onBackPressed() {


        String caller_activity = Intent_get_it.getStringExtra("caller_activity");
        if (caller_activity.equals("CustomerOrderActivity") || caller_activity.equals("RequestDriverActivity")) {
            Intent backToHome_after_order = new Intent(this, HomeActivity.class);
            backToHome_after_order.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(backToHome_after_order);
        } else {
            super.onBackPressed();
        }
    }

    public void profile_click(View view) {
        //TODO distiguish guest and customer and restaurant
        Intent fromCurrentOrderToProfile = new Intent(this, ProfileActivity.class);
        startActivity(fromCurrentOrderToProfile);
    }


    //order adapter for showing orders in listview
    private class OrderAdapter extends ArrayAdapter<Order> {
        public OrderAdapter(Context context, ArrayList<Order> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Order order = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item_of_list, parent, false);
            }
            // Lookup view for data population
            TextView tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
            TextView tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);
            // Populate the data into the template view using the data object
            String description;
            String status;
            if (order != null) {
                description= "Order Detail: <font color=\"red\">"+order.getOrder_detail() + "</font>";
                status = "Status: <font color=\"red\">" + order.getState() + "</font>";
                tvDetail.setText(Html.fromHtml(description), TextView.BufferType.SPANNABLE);
                tvStatus.setText(Html.fromHtml(status), TextView.BufferType.SPANNABLE);
            }


            // Return the completed view to render on screen
            return convertView;
        }
    }

    public class OrderChangeListener implements ValueEventListener {

        Order oneorder;
        Order oldone;

        public OrderChangeListener(Order order) {
            this.oneorder = order;
            oldone = order;
        }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String orderState = dataSnapshot.getValue(String.class);
                        oneorder.setState(orderState);
                if(orderState == null) {
                    oneorder.setState("finished");
                    CachedOrderPrefrence.updateOrderByEmail(getApplicationContext(),cur_email,oldone,oneorder);
                }
                //real time change to ui
                ordersAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CurrentOrderActivity.this,"Fail to update",Toast.LENGTH_SHORT).show();
            }
    }

}
