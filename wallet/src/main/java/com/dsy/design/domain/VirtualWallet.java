package com.dsy.design.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dsy
 * @date 2020/10/22 04:25
 */
public class VirtualWallet {
    private Long id;
    private Date createTime = new Date();
    private BigDecimal balance = BigDecimal.ZERO;
    private boolean isAllowedOverdraft = true;
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public void freeze(BigDecimal amount) {

    }

    public void unfreeze(BigDecimal amount) {

    }

    public void increaseOverdraftAmount(BigDecimal amount) {

    }

    public void decreaseOverdraftAmount(BigDecimal amount) {

    }

    public void closeOverdraft() {

    }

    public void openOverdraft() {

    }

    public BigDecimal balance() {
        return this.balance;
    }


    private BigDecimal getAvaliableBalance() {
        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvaliableBalance = this.overdraftAmount.add(totalAvaliableBalance);
        }
        return totalAvaliableBalance;
    }

    public void debit(BigDecimal amount) {
        BigDecimal totalAvaliableBalance = getAvaliableBalance();
        if (totalAvaliableBalance.compareTo(amount) < 0) {
            throw new RuntimeException("余额不足");
        }
        this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("余额不足");
        }
        this.balance.add(amount);
    }

}
