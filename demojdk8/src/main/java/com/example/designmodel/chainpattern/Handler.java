package com.example.designmodel.chainpattern;

public interface Handler {
    void handLeave(LeaveNote leaveNote);

    void setNextHandler(Handler h);
}