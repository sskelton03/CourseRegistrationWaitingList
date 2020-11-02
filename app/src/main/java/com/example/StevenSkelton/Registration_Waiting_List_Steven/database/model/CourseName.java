package com.example.StevenSkelton.Registration_Waiting_List_Steven.database.model;

public class CourseName {
    //Init table variables
    public static final String tName = "Subject";
    public static final String tableID = "TableID";
    public static final String tableTIME = "TableTime";
    public static final String priorityCase = "PriorityCase";
    public static final String tableStudent = "TableStudent";
    public static final String tableCourse = "TableCourse";

    private int TableID;
    private String TableTime;
    private String PriorityCase;
    private String TableStudent;
    private String TableCourse;
    //Create table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + tName + "("
                    + tableID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tableTIME + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + priorityCase + " TEXT,"
                    + tableStudent + " TEXT,"
                    + tableCourse + " TEXT"
                    + ")";

    public CourseName() {

    }

    public CourseName(int TableID, String TableTime, String PriorityCase, String TableStudent, String TableCourse) {
        this.TableID = TableID;
        this.TableTime = TableTime;
        this.PriorityCase = PriorityCase;
        this.TableStudent = TableStudent;
        this.TableCourse = TableCourse;


    }

    public int getTABLEID() { return TableID; }
    //Get table info
    public void setTABLEID(int TableID) {
        this.TableID = TableID;
    }
    public String getTableCOURSE() {
        return TableCourse;
    }
    public void setTableCOURSE(String TableCourse) {
        this.TableCourse = TableCourse;
    }

    public String getTableSTUDENT() {
        return TableStudent;
    }
    public void setTableSTUDENT(String TableStudent) {
        this.TableStudent = TableStudent;
    }
    public String getPriorityCASE() {
        return PriorityCase;
    }
    public void setPriorityCASE(String PriorityCase) {
        this.PriorityCase = PriorityCase;
    }
    public String getTableTIME() {
        return TableTime;
    }
    public void setTableTIME(String TableTime) {
        this.TableTime = TableTime;
    }
}
