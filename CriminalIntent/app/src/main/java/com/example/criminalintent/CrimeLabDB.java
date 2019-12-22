//package com.example.criminalintent;
//
//import android.content.Context;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
////import com.example.criminalintent.database.CrimeDbSchema.CrimeBaseHepler;
////import com.example.criminalintent.database.CrimeDbSchema.CrimeCursorWrapper;
////import com.example.criminalintent.database.CrimeDbSchema.CrimeDbSchema;
////import com.example.criminalintent.database.CrimeDbSchema.CrimeDbSchema.CrimeTable;
//
//public class CrimeLabDB {
//    private static CrimeLabDB sCrimeLab;
//    private List<Crime> mCrimes;
//
//    // 14.2 创建crime数据库
////    private Context mContext;
////    private SQLiteDatabase mDatabase;
//
//    public static CrimeLabDB get(Context context){
//        if(sCrimeLab == null){
//            sCrimeLab = new CrimeLabDB(context);
//        }
//        return sCrimeLab;
//    }
//
//    private CrimeLabDB(Context context){
//        // 14.2 创建数据库
////        mContext = context.getApplicationContext();
////        mDatabase = new CrimeBaseHepler(mContext).getWritableDatabase();
//        // 调用getWritableDatabase()方法，CrimeBaseHelper会做如下工作：
//        // 1. 打开crimeBase.db数据库，如果不存在，就先创建crimeBase.db数据库文件
//        // 2. 如果是首次创建数据库，就调用onCreate(SQLiteDatabase)方法，然后保存最新版本号
//        // 3. 如果已创建过数据库，首先检查它的版本号。如果CrimeBaseHelper中的版本号更高，
//        //    就调用onUpgrade(SQLiteDatabase, int, int)方法负责与升级相关的工作
//
//        mCrimes = new ArrayList<>();
//        for(int i = 0; i < 100; i++){
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.add(crime);
//        }
//    }
//
////    public void addCrime(Crime c){
//        // 14.4.2 插入记录
//        // insert(String, String, ContentValues)方法的第一个和第三个参数很重要。
//        // 第一个参数是数据表名(CrimeTable.NAME)，第三个是要写入的数据。
////        ContentValues values = getContentValues(c);
////        mDatabase.insert(CrimeTable.NAME, null, values);
////    }
//
//    public List<Crime> getCrimes(){
//        return mCrimes;
////        return new ArrayList<>();
//        // 14.5.2
////        List<Crime> crimes = new ArrayList<>();
////
////        CrimeCursorWrapper cursor = queryCrimes(null, null);
////
////        try{
////            cursor.moveToFirst();   //指向第一个元素
////            while(!cursor.isAfterLast()){      //没有数据可取
////                crimes.add(cursor.getCrime());
////                cursor.moveToNext();    //读取下一行记录
////            }
////        }finally {
////            cursor.close(); //关闭cursor
////        }
////
////        return crimes;
//    }
//
//    public Crime getCrime(UUID id){
//        for(Crime crime : mCrimes){
//            if(crime.getId().equals(id)){
//                return crime;
//            }
//        }
//
//        // 14.5.2
////        CrimeCursorWrapper cursor = queryCrimes(
////                CrimeTable.Cols.UUID + " = ?",
////                new String[] { id.toString()}
////        );
////
////        try{
////            if(cursor.getCount() == 0){
////                return null;
////            }
////
////            cursor.moveToFirst();
////            return cursor.getCrime();
////        }finally {
////            cursor.close();
////        }
//        return null;
//    }
//
//    // 14.4.2 更新记录
////    public void updaterCrime(Crime crime){
////        String uuidString = crime.getId().toString();
////        ContentValues values = getContentValues(crime);
//        // update(String, ContentValues, String, String[])方法类似于insert()方法，向其传入要更新的
//        // 数据表名和为表记录准备的ContentValues。
//        // 确定该更新哪条记录: 创建where子句（第三个参数），然后指定where子句中的参数值(String[]数组参数)
////        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?",
////                new String[] { uuidString });
////    }
//
//
//
//    // 14.4 写入数据库
//
////  14.4.1 ContentValues,辅助类，负责处理数据库写入和更新操作，键值存储类。
////  ContentValues的键就是数据表字段
////    private static ContentValues getContentValues(Crime crime){
////        ContentValues values = new ContentValues();
////        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
////        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
////        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
////        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
////
////        return values;
////    }
//
//    // 14.5 读取数据库
////    private Cursor queryCrimes(String whereClause, String[] whereArgs){
//    // 使用CrimeCursorWrapper类，可直接从CrimeLab中取得List<Crime>。
//    // 将查询返回的cursor封装到CrimeCursorWrapper类中，然后调用getCrimes()方法遍历取出Crime
//
//    // queryCrimes(...)方法返回CrimeCursorWrapper对象
////    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
////        Cursor cursor = mDatabase.query(
////                CrimeTable.NAME,
////                null,   //Columns - null selects all columns
////                whereClause,
////                whereArgs,
////                null,
////                null,
////                null
////        );
////
////        return new CrimeCursorWrapper(cursor);
////    }
//}
