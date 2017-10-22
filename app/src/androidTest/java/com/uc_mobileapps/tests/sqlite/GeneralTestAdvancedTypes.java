package com.uc_mobileapps.tests.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Parcel;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.uc_mobileapps.tests.sqlite.bo.AdvancedTypes;
import com.uc_mobileapps.tests.sqlite.bo.schema.AdvancedTypesSchema;
import com.uc_mobileapps.tests.sqlite.db.GeneralTestsDBOpenHelper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GeneralTestAdvancedTypes {

    private GeneralTestsDBOpenHelper openHelper;
    private SQLiteDatabase db;

    private static final byte[] BYTES = new byte[] {0,1,2,3,4,5,6,7,8,9,-1,-2,-3, -128, 127, 127};
    private static final URI TEST_URI = URI.create("ftp://example.com:21");
    private static URL TEST_URL;
    private static final Uri TEST_URI_ANDROID = Uri.parse("ftp://example.com:21");

    private static final Date SQL_DATE = new Date(-3599999L);

    private static final Time SQL_TIME = new Time(1000*(23*3600+59*60));
    private static final Time SQL_TIME_MAX = new Time(3100*365*24*23*3600*1000);

    private static final Timestamp SQL_TIMESTAMP = new Timestamp(0);

    // the nanosecond encoding will overflow with 2095:
    private static final Timestamp SQL_TIMESTAMP_MAX = new Timestamp((2094-1970)*365*24*3600*1000);

    @BeforeClass
    public static void beforeClass() {
        // ensure the database is removed and set up as new before each test
        InstrumentationRegistry.getTargetContext().deleteDatabase(GeneralTestsDBOpenHelper.DB_NAME);

        try {
            TEST_URL = new URL("http://www.example.com:8080/readme.md");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        SQL_TIMESTAMP.setNanos(12345);

        SQL_TIMESTAMP_MAX.setNanos(928);
    }

    @Before
    public void setup() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        openHelper = new GeneralTestsDBOpenHelper(appContext);
        db = openHelper.getWritableDatabase();
    }

    @Test
    public void testInsertAdvancedTypesMax() {
        db.delete(AdvancedTypesSchema.TBL_ADVANCED_TYPES, null, null);

        AdvancedTypes advancedTypes = new AdvancedTypes();

        advancedTypes.setTypeBinary(BYTES);
        advancedTypes.setTypeUri(TEST_URI);
        advancedTypes.setTypeUrl(TEST_URL);
        advancedTypes.setTypeUriAndroid(TEST_URI_ANDROID);
        advancedTypes.setTypeSqlDate(SQL_DATE);
        advancedTypes.setTypeSqlTimestamp(SQL_TIMESTAMP_MAX);
        advancedTypes.setTypeSqlTime(SQL_TIME_MAX);

        db.insert(AdvancedTypesSchema.TBL_ADVANCED_TYPES, null, AdvancedTypesSchema.instance().getContentValues(advancedTypes));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(AdvancedTypesSchema.TBL_ADVANCED_TYPES);

        try (Cursor cursor =
                     queryBuilder.query(db, AdvancedTypesSchema.COLUMNS, null, null, null, null, null)) {
            assertTrue(cursor.moveToNext());

            AdvancedTypes advancedTypes2 = AdvancedTypesSchema.instance().readFromCursor(cursor, new AdvancedTypes());
            assertFalse(cursor.moveToNext());

            assertTrue(Arrays.equals(BYTES, advancedTypes2.getTypeBinary()));
            assertEquals(TEST_URI,    advancedTypes2.getTypeUri());
            assertEquals(TEST_URL,   advancedTypes2.getTypeUrl());
            assertEquals(TEST_URI_ANDROID,  advancedTypes2.getTypeUriAndroid());
            assertEquals("sql date", SQL_DATE,   advancedTypes2.getTypeSqlDate());
            assertEquals("sql time", SQL_TIME_MAX,    advancedTypes2.getTypeSqlTime());
            assertEquals("sql timestamp", SQL_TIMESTAMP_MAX, advancedTypes2.getTypeSqlTimestamp());
        }
    }

    @Test
    public void testInsertAdvancedTypesMin() {
        db.delete(AdvancedTypesSchema.TBL_ADVANCED_TYPES, null, null);

        AdvancedTypes advancedTypes = new AdvancedTypes();

        advancedTypes.setTypeBinary(BYTES);
        advancedTypes.setTypeUri(TEST_URI);
        advancedTypes.setTypeUrl(TEST_URL);
        advancedTypes.setTypeUriAndroid(TEST_URI_ANDROID);
        advancedTypes.setTypeSqlDate(SQL_DATE);

        advancedTypes.setTypeSqlTimestamp(SQL_TIMESTAMP);
        advancedTypes.setTypeSqlTime(SQL_TIME);

        db.insert(AdvancedTypesSchema.TBL_ADVANCED_TYPES, null, AdvancedTypesSchema.instance().getContentValues(advancedTypes));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(AdvancedTypesSchema.TBL_ADVANCED_TYPES);

        try (Cursor cursor =
                     queryBuilder.query(db, AdvancedTypesSchema.COLUMNS, null, null, null, null, null)) {
            assertTrue(cursor.moveToNext());

            AdvancedTypes advancedTypes2 = AdvancedTypesSchema.instance().readFromCursor(cursor, new AdvancedTypes());
            assertFalse(cursor.moveToNext());

            assertTrue(Arrays.equals(BYTES, advancedTypes2.getTypeBinary()));
            assertEquals(TEST_URI,    advancedTypes2.getTypeUri());
            assertEquals(TEST_URL,   advancedTypes2.getTypeUrl());
            assertEquals(TEST_URI_ANDROID,  advancedTypes2.getTypeUriAndroid());
            assertEquals(SQL_DATE,   advancedTypes2.getTypeSqlDate());
            assertEquals(SQL_TIME,    advancedTypes2.getTypeSqlTime());
            assertEquals(SQL_TIMESTAMP, advancedTypes2.getTypeSqlTimestamp());
        }
    }

    @Test
    public void testInsertAdvancedTypesNull() {
        db.delete(AdvancedTypesSchema.TBL_ADVANCED_TYPES, null, null);

        AdvancedTypes advancedTypes = new AdvancedTypes();

        db.insert(AdvancedTypesSchema.TBL_ADVANCED_TYPES, null, AdvancedTypesSchema.instance().getContentValues(advancedTypes));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(AdvancedTypesSchema.TBL_ADVANCED_TYPES);

        try (Cursor cursor =
                     queryBuilder.query(db, AdvancedTypesSchema.COLUMNS, null, null, null, null, null)) {
            assertTrue(cursor.moveToNext());

            AdvancedTypes advancedTypes2 = AdvancedTypesSchema.instance().readFromCursor(cursor, new AdvancedTypes());
            assertFalse(cursor.moveToNext());

            assertNull(advancedTypes2.getTypeBinary());
            assertNull(advancedTypes2.getTypeUri());
            assertNull(advancedTypes2.getTypeUrl());
            assertNull(advancedTypes2.getTypeUriAndroid());
            assertNull(advancedTypes2.getTypeSqlDate());
            assertNull(advancedTypes2.getTypeSqlTime());
            assertNull(advancedTypes2.getTypeSqlTimestamp());
        }
    }

    @Test
    public void testParcelableAdvancedTypes() {

        AdvancedTypes advancedTypes = new AdvancedTypes();
        advancedTypes.setId(-1L);
        advancedTypes.setTypeBinary(BYTES);
        advancedTypes.setTypeUri(TEST_URI);
        advancedTypes.setTypeUrl(TEST_URL);
        advancedTypes.setTypeUriAndroid(TEST_URI_ANDROID);
        advancedTypes.setTypeSqlDate(SQL_DATE);

        advancedTypes.setTypeSqlTimestamp(SQL_TIMESTAMP);
        advancedTypes.setTypeSqlTime(SQL_TIME);

        AdvancedTypes advancedTypes2 = new AdvancedTypes();

        Parcel parcel = Parcel.obtain();
        try {
            advancedTypes.writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            advancedTypes2.readFromParcel(parcel);
        } finally {
            parcel.recycle();
        }

        assertTrue("binary", Arrays.equals(advancedTypes.getTypeBinary(), advancedTypes2.getTypeBinary()));
        assertEquals("uri", TEST_URI,    advancedTypes2.getTypeUri());
        assertEquals("url", TEST_URL,   advancedTypes2.getTypeUrl());
        assertEquals("uri android", TEST_URI_ANDROID,  advancedTypes2.getTypeUriAndroid());
        assertEquals("sql date", advancedTypes.getTypeSqlDate(), advancedTypes2.getTypeSqlDate());
        assertEquals("sql timestamp", advancedTypes.getTypeSqlTimestamp(), advancedTypes2.getTypeSqlTimestamp());
        assertEquals("sql time", advancedTypes.getTypeSqlTime(), advancedTypes2.getTypeSqlTime());
        assertEquals("nullable id", advancedTypes.getId(), advancedTypes2.getId());
    }
}