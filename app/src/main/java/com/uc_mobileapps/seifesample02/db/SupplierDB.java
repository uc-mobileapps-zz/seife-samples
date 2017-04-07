/**  * 
 */
package com.uc_mobileapps.seifesample02.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.uc_mobileapps.seifesample02.provider.SupplierProvider;

import com.uc_mobileapps.seifesample02.delivery.schema.DeliverySchema;
import com.uc_mobileapps.seifesample02.delivery.schema.ProductSchema;

/**
 * Database helper that creates and/or upgrades the schema
 */
public class SupplierDB extends SQLiteOpenHelper {

	/**
	 * Name of the database
	 */
	public static final String DB_NAME = "SupplierDB.db";

	/**
	 * The version of this database for the current release
	 */
	private static final int DB_VERSION = 1;

	/**
	 * Default constructor for the helper
	 */
	public SupplierDB(Context paramContext) {
		this(paramContext, DB_NAME, null, DB_VERSION);
	}

	protected SupplierDB(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt) {
		super(paramContext, paramString, paramCursorFactory, paramInt);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		seifeCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		seifeUpgrade(db, oldVersion, newVersion);
	}

	//[begin seife autogenerated@

	/**
	 * Outsourced data creation logic of automatically created tables, see {@see #onCreate(SQLiteDatabase)}
	 */
	public void seifeCreate(SQLiteDatabase db) { 
		for (String ddl : DeliverySchema.instance().getTableScripts()) {
			db.execSQL(ddl);
		}
 
		for (String ddl : ProductSchema.instance().getTableScripts()) {
			db.execSQL(ddl);
		}
	}
	
	/**
	 * Outsourced data update logic of automatically created tables, see {@see #onUpgrade(SQLiteDatabase,int,int)}
	 */
	public void seifeUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	//@end seife autogenerated]
}

