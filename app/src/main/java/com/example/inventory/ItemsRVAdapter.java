package com.example.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ItemsRVAdapter extends RecyclerView.Adapter<ItemsRVAdapter.ViewHolder> {
	// creating variables for our ArrayList and context
	private ArrayList<Items> ItemsArrayList;
	private Context context;

	// creating constructor for our adapter class
	public ItemsRVAdapter(ArrayList<Items> ItemsArrayList, Context context) {
		this.ItemsArrayList = ItemsArrayList;
		this.context = context;
	}

	@NonNull
	@Override
	public ItemsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		// passing our layout file for displaying our card item
		return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ItemsRVAdapter.ViewHolder holder, int position) {
		// setting data to our text views from our modal class.
		Items Items = ItemsArrayList.get(position);
		holder.name.setText(Items.getName());
		holder.description.setText(Items.getDescription());
		holder.price.setText(Items.getSell()+"");
setImage(holder, Items);
	}

	@Override
	public int getItemCount() {
		// returning the size of our array list.
		return ItemsArrayList.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		// creating variables for our text views.
		private final TextView name;
		private final TextView description;
		private final TextView price;
		private final ImageView image;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			// initializing our text views.
			name = itemView.findViewById(R.id.name);
			description = itemView.findViewById(R.id.description);
			price = itemView.findViewById(R.id.price);
			image = itemView.findViewById(R.id.image);
		}
	}

	public void setImage(ItemsRVAdapter.ViewHolder holder, Items item){
		FirebaseDatabase firebaseDatabase
				= FirebaseDatabase.getInstance();

		// we will get a DatabaseReference for the database
		// root node
		DatabaseReference databaseReference
				= firebaseDatabase.getReference();

		// Here "image" is the child node value we are
		// getting child node data in the getImage variable
		DatabaseReference getImage
				= databaseReference.child("Items");

		// Adding listener for a single change
		// in the data at this location.
		// this listener will triggered once
		// with the value of the data at the location
		getImage.addListenerForSingleValueEvent(
				new ValueEventListener() {
					@Override
					public void onDataChange(
							@NonNull DataSnapshot dataSnapshot)
					{
						// getting a DataSnapshot for the
						// location at the specified relative
						// path and getting in the link variable
						String link = item.getId();

						// loading that data into rImage
						// variable which is ImageView
						ImageView image = holder.image;

						Glide.with(context).load(link).into(image);

					}

					// this will called when any problem
					// occurs in getting data
					@Override
					public void onCancelled(
							@NonNull DatabaseError databaseError)
					{
						// we are showing that error message in
						// toast
						Toast
								.makeText(context,
										"Error Loading Image",
										Toast.LENGTH_SHORT)
								.show();
					}
				});
	}
}
