package com.uc_mobileapps.seifesample01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.uc_mobileapps.seifesample01.bo.Customer;
import com.uc_mobileapps.seifesample01.bo.Order;
import com.uc_mobileapps.seifesample01.bo.schema.CustomerSchema;
import com.uc_mobileapps.seifesample01.bo.schema.OrderSchema;
import com.uc_mobileapps.seifesample01.db.CustomerDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Shows how constants and the slim API is used,
 * simple tests of the SQLite database layer and {@link android.database.sqlite.SQLiteOpenHelper}.
 */
@RunWith(AndroidJUnit4.class)
public class SQLiteSchemaTest {

    @Before
    public void setup() {
        // ensure the database is removed and set up as new before each test
        InstrumentationRegistry.getTargetContext().deleteDatabase(CustomerDB.DB_NAME);
    }

    @Test
    public void simpleInsertQuery() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        CustomerDB customerDB = new CustomerDB(appContext);
        SQLiteDatabase db = customerDB.getWritableDatabase();
        db.delete(CustomerSchema.TBL_CUSTOMER, null, null);

        Customer customer = new Customer();
        customer.setName("New customer");

        db.insert(CustomerSchema.TBL_CUSTOMER, null, CustomerSchema.instance().getContentValues(customer));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CustomerSchema.TBL_CUSTOMER);

        try (Cursor cursor =
                     queryBuilder.query(db, CustomerSchema.COLUMNS, null, null, null, null, null)) {
            assertTrue(cursor.moveToNext());

            Customer customer1 = CustomerSchema.instance().readFromCursor(cursor, new Customer());
            assertFalse(cursor.moveToNext());
            assertEquals(customer.getName(), customer1.getName());
        }
    }


    @Test
    public void simplePurchaseOrderTest() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        CustomerDB customerDB = new CustomerDB(appContext);
        SQLiteDatabase db = customerDB.getWritableDatabase();
        db.delete(CustomerSchema.TBL_CUSTOMER, null, null);

        Customer customer = new Customer();
        customer.setName("New customer");

        long customerId = db.insert(CustomerSchema.TBL_CUSTOMER, null,
                CustomerSchema.instance().getContentValues(customer));
        customer.setId(customerId);

        Order order = new Order();
        order.setCustomer(customer);

        assertEquals(Long.valueOf(customerId), order.getCustomerId());

        order.setOrderDate(new Date());

        db.insert(OrderSchema.TBL_ORDER, null,
                OrderSchema.instance().getContentValues(order));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(OrderSchema.TBL_ORDER + " INNER " + OrderSchema.JOIN_CUSTOMER);

        try (Cursor cursor =
                     queryBuilder.query(db, new String[]{CustomerSchema.COL_ID_FQN, CustomerSchema.COL_NAME_FQN, OrderSchema.COL_ORDER_DATE_FQN},
                null, null,
                null, null, null);
        ) {
            assertTrue(cursor.moveToNext());

            Customer customer1 = CustomerSchema.instance().readFromCursor(cursor, new Customer());
            Order order1 = OrderSchema.instance().readFromCursor(cursor, new Order());
            assertFalse(cursor.moveToNext());
            assertEquals(customer.getName(), customer1.getName());
            assertEquals(order.getOrderDate(), order1.getOrderDate());
        }
    }
}
