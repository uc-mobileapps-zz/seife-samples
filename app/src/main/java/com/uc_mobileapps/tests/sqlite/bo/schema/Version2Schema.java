package com.uc_mobileapps.tests.sqlite.bo.schema;
import com.uc_mobileapps.tests.sqlite.bo.Version2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.List;
import java.util.ArrayList;

import android.os.Parcelable;

import android.os.Parcel;

public class Version2Schema { 
	//[begin seife autogenerated@

	/**
	 * Table name of the Version2 table
	 */
	public static String TBL_VERSION2 = "version2";
	

	public static String COL_ID = "id";

	public static String COL_ADDED_IN_V3 = "addedInV3";

	/** Fully qualified column name of {@link #COL_ID */
	public static String COL_ID_FQN = "version2.id";

	/** Fully qualified column name of {@link #COL_ADDED_IN_V3 */
	public static String COL_ADDED_IN_V3_FQN = "version2.addedInV3";
	
	/**
	 * All columns
	 */
	public static String[] COLUMNS = new String[] { COL_ID, COL_ADDED_IN_V3	};

	/**
	 * Table creation script
	 */
	public static final String SQL_CREATE_TABLE_VERSION2 =
			"create table " + TBL_VERSION2 + " (" + 

					COL_ID + " integer primary key autoincrement," +
					COL_ADDED_IN_V3 + " text" +
					")";


 

	private static Version2Schema schema = new Version2Schema();
	public static Version2Schema instance() {
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
	public ContentValues getContentValues(Version2 bo) {
		ContentValues contentValues = new ContentValues();

		if (bo.getId() != null) {
			contentValues.put(COL_ID, bo.getId());
		}
		contentValues.put(COL_ADDED_IN_V3, bo.getAddedInV3());
		return contentValues;
	}

	/**
	 * Sets all attributes from the cursor
	 * @param cursorFrom the cursor to read from
	 * @param bo may be null
	 * @return the bo passed as a parameter or a new instance
	 */
	public Version2 readFromCursor(Cursor cursorFrom, Version2 bo)
	{
		if (bo == null) {
			bo = new Version2();
		}
		final Cursor c = cursorFrom; 

		bo.setId(c.isNull(c.getColumnIndex(COL_ID)) ? null : c.getLong(c.getColumnIndex(COL_ID)));
		bo.setAddedInV3(c.getString(c.getColumnIndex(COL_ADDED_IN_V3)));
		return bo;
	}
	
	
	/**
	 * @return hard-coded table creation and index scripts
	 */
	public List<String> getTableScripts() {
		List<String> result = new ArrayList<String>();
		result.add(SQL_CREATE_TABLE_VERSION2); 
		return result;
	}

 
	//@end seife autogenerated]


	
}
