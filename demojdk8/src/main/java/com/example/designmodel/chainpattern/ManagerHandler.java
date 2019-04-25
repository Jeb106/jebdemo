package com.example.designmodel.chainpattern;

public class ManagerHandler implements Handler {
    private Handler nextHandler;

    public void handLeave(LeaveNote leaveNote) {
        System.out.println("总经理同意" + leaveNote.getName() + "申请请假" + leaveNote.getLeaverDayNum() + "天,原因:" + leaveNote.getLeaveReason());
    }

    public void setNextHandler(Handler h) {
        nextHandler = h;
    }
}
