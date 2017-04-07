package com.uc_mobileapps.seifesample01;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.uc_mobileapps.seifesample01.bo.Customer;
import com.uc_mobileapps.seifesample01.bo.schema.CustomerSchema;
import com.uc_mobileapps.seifesample01.db.CustomerDB;
import com.uc_mobileapps.seifesample01.provider.CustomerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Shows how constants and the generated DataProvider is used,
 * simple tests.
 */
@RunWith(AndroidJUnit4.class)
public class DataProviderTest {

    @Before
    public void setup() {
        // ensure the database is removed and set up as new before each test
        InstrumentationRegistry.getTargetContext().deleteDatabase(CustomerDB.DB_NAME);
    }

    @Test
    public void simpleProviderInsertThenQuery() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();


        CustomerDB customerDB = new CustomerDB(appContext);
        SQLiteDatabase db = customerDB.getWritableDatabase();
        db.delete(CustomerSchema.TBL_CUSTOMER, null, null);

        Customer customer = new Customer();
        customer.setName("New customer");

        ContentResolver resolver = appContext.getContentResolver();
        Uri uriCustomerData = resolver.insert(
                    CustomerProvider
                            .CONTENT_URI_CUSTOMER,
                    CustomerSchema.instance().getContentValues(customer));

        Cursor cursor = resolver.query(CustomerProvider.CONTENT_URI_CUSTOMER, CustomerSchema.COLUMNS,
                CustomerSchema.COL_NAME + " like ?", new String[]{"New%"}, null);
        try {
            assertTrue(cursor.moveToNext());

            Customer customer1 = CustomerSchema.instance().readFromCursor(cursor, new Customer());
            assertFalse(cursor.moveToNext());
            assertEquals(customer.getName(), customer1.getName());
        } finally {
            cursor.close();
        }
    }


    @Test
    public void simpleProviderGetById() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        CustomerDB customerDB = new CustomerDB(appContext);
        SQLiteDatabase db = customerDB.getWritableDatabase();
        db.delete(CustomerSchema.TBL_CUSTOMER, null, null);

        Customer customer = new Customer();
        customer.setName("New customer");

        ContentResolver resolver = appContext.getContentResolver();
        Uri uriCustomerData = resolver.insert(
                CustomerProvider.CONTENT_URI_CUSTOMER,
                CustomerSchema.instance().getContentValues(customer));

        String customerId = uriCustomerData.getLastPathSegment();


        Uri queryCustomerById = CustomerProvider.CONTENT_URI_CUSTOMER
                .buildUpon().appendQueryParameter(CustomerSchema.COL_ID, customerId)
                .build();

        try (Cursor cursor = resolver.query(queryCustomerById, CustomerSchema.COLUMNS, null, null, null)) {
            assertTrue(cursor.moveToNext());

            Customer customer1 = CustomerSchema.instance().readFromCursor(cursor, new Customer());

            assertEquals(customerId, String.valueOf(customer1.getId()));
            assertEquals(customer.getName(), customer1.getName());
            assertFalse(cursor.moveToNext());
        }

    }
}
