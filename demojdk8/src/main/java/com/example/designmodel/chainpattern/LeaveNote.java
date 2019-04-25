package com.example.designmodel.chainpattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 请假实体
 */
@Data
@AllArgsConstructor
public class LeaveNote {
    private String name;
    private String leaveReason;
    private int leaverDayNum;
}

