package com.dsy.design.controller;

import com.dsy.design.service.AnemiaVirtualWalletService;

import java.math.BigDecimal;

/**
 * @author dsy
 * @date 2020/10/22 01:55
 */
public class AnemiaVirtualWalletController {

    private AnemiaVirtualWalletService anemiaVirtualWalletService;


    /**
     * 查询余额
     * @param walletId
     * @return
     */
    public BigDecimal getBalance(Long walletId) {
        return null;
    }

    /**
     * 出账
     * @param walletId
     * @param amount
     */
    public void debit(Long walletId,BigDecimal amount){}


    /**
     * 入账
     * @param walletId
     * @param amount
     */
    public void credit(Long walletId, BigDecimal amount) {

    }

    /**
     * 转掌
     * @param fromWalletId
     * @param toWalletId
     * @param amount
     */
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {

    }

}
