package com.uc_mobileapps.seifesample01.bo.schema;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.uc_mobileapps.seifesample01.bo.Customer;
import com.uc_mobileapps.seifesample01.bo.Order;
import com.uc_mobileapps.seifesample01.provider.CustomerProvider;

import java.util.ArrayList;
import java.util.List;

public class OrderSchema { 
	//[begin seife autogenerated@

	/**
	 * Table name of the Order table
	 */
	public static String TBL_ORDER = "purchaseOrder";
	

	public static String COL_ID = "id";

	public static String COL_FK_CUSTOMER_CUSTOMER_ID = "customerId";

	public static String COL_ORDER_DATE = "orderDate";

	/** Fully qualified column name of {@link #COL_ID */
	public static String COL_ID_FQN = "purchaseOrder.id";

	/** Fully qualified column name of {@link #COL_FK_CUSTOMER_CUSTOMER_ID */
	public static String COL_FK_CUSTOMER_CUSTOMER_ID_FQN = "purchaseOrder.customerId";

	/** Fully qualified column name of {@link #COL_ORDER_DATE */
	public static String COL_ORDER_DATE_FQN = "purchaseOrder.orderDate";
	
	/**
	 * All columns
	 */
	public static String[] COLUMNS = new String[] { COL_ID, COL_FK_CUSTOMER_CUSTOMER_ID, COL_ORDER_DATE	};

	/**
	 * Table creation script
	 */
	public static final String SQL_CREATE_TABLE_ORDER =
			"create table " + TBL_ORDER + " (" + 

					COL_ID + " integer primary key autoincrement," +
					COL_FK_CUSTOMER_CUSTOMER_ID + " integer," +
					COL_ORDER_DATE + " integer not null" +
					", " +
					" FOREIGN KEY(" + COL_FK_CUSTOMER_CUSTOMER_ID  + ")" +
					" REFERENCES " + CustomerSchema.TBL_CUSTOMER 
					+"(" + CustomerSchema.COL_ID  + ")" +			 		
					")";

	/**
	 * Index for idxOrderDate
	 */
	public static final String SQL_CREATE_IDX_ORDER_IDX_ORDER_DATE = 
			"create index if not exists "
					+ TBL_ORDER
					+ "_idx_idxOrderDate ON " + TBL_ORDER
					+ "(" + COL_ORDER_DATE + ")";

	public static final String JOIN_CUSTOMER =	
		"JOIN " + CustomerSchema.TBL_CUSTOMER + 
		" ON (" + COL_FK_CUSTOMER_CUSTOMER_ID_FQN + " = " + CustomerSchema.COL_ID_FQN  + ")";  

	private static OrderSchema schema = new OrderSchema();
	public static OrderSchema instance() {
		return schema;
	}

	/**
	 * Checks for mandatory constraints defined on fields
	 */
	public boolean checkConstraints(ContentValues contentValues) {
		return true;
	}
	
	/**
	 * Gets all attribute values of the bo as key value pairs
	 * @param bo may not be null
	 * @return new instance of {@link ContentValues}
	 */
	public ContentValues getContentValues(Order bo) {
		ContentValues contentValues = new ContentValues();

		if (bo.getId() != null) {
			contentValues.put(COL_ID, bo.getId());
		}
		contentValues.put(COL_FK_CUSTOMER_CUSTOMER_ID, bo.getCustomerId());
		contentValues.put(COL_ORDER_DATE, (bo.getOrderDate()!=null) ? bo.getOrderDate().getTime() : null);
		return contentValues;
	}

	/**
	 * Sets all attributes from the cursor
	 * @param cursorFrom the cursor to read from
	 * @param bo may be null
	 * @return the bo passed as a parameter or a new instance
	 */
	public Order readFromCursor(Cursor cursorFrom, Order bo)
	{
		if (bo == null) {
			bo = new Order();
		}
		final Cursor c = cursorFrom; 

		bo.setId(c.isNull(c.getColumnIndex(COL_ID)) ? null : c.getLong(c.getColumnIndex(COL_ID)));
		bo.setCustomerId(c.isNull(c.getColumnIndex(COL_FK_CUSTOMER_CUSTOMER_ID)) ? null : c.getLong(c.getColumnIndex(COL_FK_CUSTOMER_CUSTOMER_ID)));
		bo.setOrderDate(c.isNull(c.getColumnIndex(COL_ORDER_DATE)) ? null : new java.util.Date(c.getLong(c.getColumnIndex(COL_ORDER_DATE))));
		return bo;
	}
	
	
	/**
	 * @return hard-coded table creation and index scripts
	 */
	public List<String> getTableScripts() {
		List<String> result = new ArrayList<String>();
		result.add(SQL_CREATE_TABLE_ORDER); 
		result.add(SQL_CREATE_IDX_ORDER_IDX_ORDER_DATE);
		return result;
	}

	/** 
	 * Resolves the Customer instance for the Order#customer field
	 * @param context the ContentResolver is obtained from there
	 * @param bo the business object to get the foreign key reference from
	 * @return
	 */
	public Customer resolveCustomerField(Context context, Order bo) {
		ContentResolver contentResolver = context.getContentResolver();
		String selection = null;
		String[] selectionArgs = null;

		Uri uri = CustomerProvider.getContentUriCustomer().buildUpon()
				.appendQueryParameter(CustomerSchema.COL_ID, String.valueOf(bo.getId()))
				.build();
		Cursor cursor = contentResolver.query(uri, CustomerSchema.COLUMNS, selection, selectionArgs, null);
		try {
			Customer foreignInstance = 
					CustomerSchema.instance().readFromCursor(cursor, new Customer());
			//bo.setCustomer(foreignInstance);
			return foreignInstance;
		} finally {
			cursor.close();
		}
	}
  
	//@end seife autogenerated]




	
}
