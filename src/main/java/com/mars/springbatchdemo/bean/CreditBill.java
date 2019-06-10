package com.mars.springbatchdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditBill {
    private String accountId;
    private String name;
    private double amount;
    private String date;
    private String address;
}
