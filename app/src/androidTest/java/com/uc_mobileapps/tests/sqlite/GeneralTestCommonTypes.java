package com.uc_mobileapps.tests.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.uc_mobileapps.tests.sqlite.bo.CommonTypes;
import com.uc_mobileapps.tests.sqlite.bo.schema.CommonTypesSchema;
import com.uc_mobileapps.tests.sqlite.db.GeneralTestsDBOpenHelper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GeneralTestCommonTypes {

    private GeneralTestsDBOpenHelper openHelper;
    private SQLiteDatabase db;

    @BeforeClass
    public static void beforeClass() {
        // ensure the database is removed and set up as new before each test
        InstrumentationRegistry.getTargetContext().deleteDatabase(GeneralTestsDBOpenHelper.DB_NAME);
    }

    @Before
    public void setup() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        openHelper = new GeneralTestsDBOpenHelper(appContext);
        db = openHelper.getWritableDatabase();
    }

    @Test
    public void testInsertCommonTypesMax() {
        db.delete(CommonTypesSchema.TBL_COMMON_TYPES, null, null);

        CommonTypes commonTypes = new CommonTypes();

        commonTypes.setTypeBoolean(true);
        commonTypes.setTypeByte(Byte.MAX_VALUE);
        commonTypes.setTypeShort(Short.MAX_VALUE);
        commonTypes.setTypeDouble(Double.MAX_VALUE);
        commonTypes.setTypeFloat(Float.MAX_VALUE);

        commonTypes.setTypeInt(Integer.MAX_VALUE);
        commonTypes.setTypeLong(Long.MAX_VALUE);

        commonTypes.setTypeNullableBoolean(Boolean.TRUE);
        commonTypes.setTypeNullableByte(Byte.valueOf(Byte.MAX_VALUE));
        commonTypes.setTypeNullableShort(Short.valueOf(Short.MAX_VALUE));
        commonTypes.setTypeNullableDouble(Double.valueOf(Double.MAX_VALUE));
        commonTypes.setTypeNullableFloat(Float.valueOf(Float.MAX_VALUE));
        commonTypes.setTypeNullableInt(Integer.valueOf(Integer.MAX_VALUE));
        commonTypes.setTypeNullableLong(Long.valueOf(Long.MAX_VALUE));

        db.insert(CommonTypesSchema.TBL_COMMON_TYPES, null, CommonTypesSchema.instance().getContentValues(commonTypes));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CommonTypesSchema.TBL_COMMON_TYPES);

        try (Cursor cursor =
                     queryBuilder.query(db, CommonTypesSchema.COLUMNS, null, null, null, null, null)) {
            assertTrue(cursor.moveToNext());

            CommonTypes commonTypes2 = CommonTypesSchema.instance().readFromCursor(cursor, new CommonTypes());
            assertFalse(cursor.moveToNext());

            assertEquals(true, commonTypes2.isTypeBoolean());
            assertEquals(Byte.MAX_VALUE,    commonTypes2.getTypeByte());
            assertEquals(Short.MAX_VALUE,   commonTypes2.getTypeShort());
            assertEquals(Double.MAX_VALUE,  commonTypes2.getTypeDouble(), 0.0000000000001);
            assertEquals(Float.MAX_VALUE,   commonTypes2.getTypeFloat(), 0.0000000000001);
            assertEquals(Integer.MAX_VALUE, commonTypes2.getTypeInt());
            assertEquals(Long.MAX_VALUE,    commonTypes2.getTypeLong());
            assertEquals(Boolean.TRUE,      commonTypes2.getTypeNullableBoolean());
            assertEquals(Byte.valueOf(Byte.MAX_VALUE),      commonTypes2.getTypeNullableByte());
            assertEquals(Short.valueOf(Short.MAX_VALUE),    commonTypes2.getTypeNullableShort());
            assertEquals(Double.valueOf(Double.MAX_VALUE),  commonTypes2.getTypeNullableDouble());
            assertEquals(Float.valueOf(Float.MAX_VALUE),    commonTypes2.getTypeNullableFloat());
            assertEquals(Integer.valueOf(Integer.MAX_VALUE),commonTypes2.getTypeNullableInt());
            assertEquals(Long.valueOf(Long.MAX_VALUE),      commonTypes2.getTypeNullableLong());
        }
    }

    @Test
    public void testInsertCommonTypesMin() {
        db.delete(CommonTypesSchema.TBL_COMMON_TYPES, null, null);

        CommonTypes commonTypes = new CommonTypes();

        commonTypes.setTypeBoolean(false);
        commonTypes.setTypeByte(Byte.MIN_VALUE);
        commonTypes.setTypeShort(Short.MIN_VALUE);
        commonTypes.setTypeDouble(Double.MIN_VALUE);
        commonTypes.setTypeFloat(Float.MIN_VALUE);

        commonTypes.setTypeInt(Integer.MIN_VALUE);
        commonTypes.setTypeLong(Long.MIN_VALUE);

        commonTypes.setTypeNullableBoolean(Boolean.FALSE);
        commonTypes.setTypeNullableByte(Byte.valueOf(Byte.MIN_VALUE));
        commonTypes.setTypeNullableShort(Short.valueOf(Short.MIN_VALUE));
        commonTypes.setTypeNullableDouble(Double.valueOf(Double.MIN_VALUE));
        commonTypes.setTypeNullableFloat(Float.valueOf(Float.MIN_VALUE));
        commonTypes.setTypeNullableInt(Integer.valueOf(Integer.MIN_VALUE));
        commonTypes.setTypeNullableLong(Long.valueOf(Long.MIN_VALUE));

        db.insert(CommonTypesSchema.TBL_COMMON_TYPES, null, CommonTypesSchema.instance().getContentValues(commonTypes));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CommonTypesSchema.TBL_COMMON_TYPES);

        try (Cursor cursor =
                     queryBuilder.query(db, CommonTypesSchema.COLUMNS, null, null, null, null, null)) {
            assertTrue(cursor.moveToNext());

            CommonTypes commonTypes2 = CommonTypesSchema.instance().readFromCursor(cursor, new CommonTypes());
            assertFalse(cursor.moveToNext());

            assertEquals(false, commonTypes2.isTypeBoolean());
            assertEquals(Byte.MIN_VALUE,    commonTypes2.getTypeByte());
            assertEquals(Short.MIN_VALUE,   commonTypes2.getTypeShort());
            assertEquals(Double.MIN_VALUE,  commonTypes2.getTypeDouble(), 0.0000000000001);
            assertEquals(Float.MIN_VALUE,   commonTypes2.getTypeFloat(), 0.0000000000001);
            assertEquals(Integer.MIN_VALUE, commonTypes2.getTypeInt());
            assertEquals(Long.MIN_VALUE,    commonTypes2.getTypeLong());
            assertEquals(Boolean.FALSE,      commonTypes2.getTypeNullableBoolean());
            assertEquals(Byte.valueOf(Byte.MIN_VALUE),      commonTypes2.getTypeNullableByte());
            assertEquals(Short.valueOf(Short.MIN_VALUE),    commonTypes2.getTypeNullableShort());
            assertEquals(Double.valueOf(Double.MIN_VALUE),  commonTypes2.getTypeNullableDouble());
            assertEquals(Float.valueOf(Float.MIN_VALUE),    commonTypes2.getTypeNullableFloat());
            assertEquals(Integer.valueOf(Integer.MIN_VALUE),commonTypes2.getTypeNullableInt());
            assertEquals(Long.valueOf(Long.MIN_VALUE),      commonTypes2.getTypeNullableLong());
        }
    }

    @Test
    public void testInsertCommonTypesNull() {
        db.delete(CommonTypesSchema.TBL_COMMON_TYPES, null, null);

        CommonTypes commonTypes = new CommonTypes();

        commonTypes.setTypeNullableBoolean(null);
        commonTypes.setTypeNullableByte(null);
        commonTypes.setTypeNullableShort(null);
        commonTypes.setTypeNullableDouble(null);
        commonTypes.setTypeNullableFloat(null);
        commonTypes.setTypeNullableInt(null);
        commonTypes.setTypeNullableLong(null);

        db.insert(CommonTypesSchema.TBL_COMMON_TYPES, null, CommonTypesSchema.instance().getContentValues(commonTypes));

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CommonTypesSchema.TBL_COMMON_TYPES);

        try (Cursor cursor =
                     queryBuilder.query(db, CommonTypesSchema.COLUMNS, null, null, null, null, null)) {
            assertTrue(cursor.moveToNext());

            CommonTypes commonTypes2 = CommonTypesSchema.instance().readFromCursor(cursor, new CommonTypes());
            assertFalse(cursor.moveToNext());

            assertEquals(null, commonTypes2.getTypeNullableBoolean());
            assertEquals(null, commonTypes2.getTypeNullableByte());
            assertEquals(null, commonTypes2.getTypeNullableShort());
            assertEquals(null, commonTypes2.getTypeNullableDouble());
            assertEquals(null, commonTypes2.getTypeNullableFloat());
            assertEquals(null, commonTypes2.getTypeNullableInt());
            assertEquals(null, commonTypes2.getTypeNullableLong());
        }
    }

}