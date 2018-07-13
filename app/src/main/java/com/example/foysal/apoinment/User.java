package com.example.foysal.apoinment;



public class User {
    public String name;
    public String type;
    public String email;
    public String avata;
    public String age;
    public Status status;
    public Message message;
    public BpCheckup bp;


    public User(){
        status = new Status();
        message = new Message();
        bp=new BpCheckup();
        status.isOnline = false;
        status.timestamp = 0;
        message.idReceiver = "0";
        message.idSender = "0";
        message.text = "";
        message.timestamp = 0;
        bp.systolic=0;
        bp.diastolic=0;
        bp.pulse=0;
        bp.timestamp=0;
    }
}
