package com.dsy.design.service;

import com.dsy.design.bo.AnemiaVirtualWalletBo;
import com.dsy.design.entity.VirtualWalletEntity;
import com.dsy.design.entity.VirtualWalletTransactionEntity;
import com.dsy.design.repository.VirtualWalletRepository;
import com.dsy.design.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dsy
 * @date 2020/10/22 02:06
 */
public class AnemiaVirtualWalletService {

    private VirtualWalletRepository walletRepository;

    private VirtualWalletTransactionRepository transactionRepository;


    public AnemiaVirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity virtualWalletEntity = walletRepository.getWalletEntity(walletId);
        AnemiaVirtualWalletBo walletBo = convert();
        return walletBo;
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepository.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new RuntimeException("账户余额不足");
        }
        walletRepository.updateBalance(walletId, balance.subtract(amount));
    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        walletRepository.updateBalance(walletId, balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity virtualWalletTransactionEntity = new VirtualWalletTransactionEntity();
        virtualWalletTransactionEntity.setAmount(amount);
        virtualWalletTransactionEntity.setCreateTime(new Date());
        virtualWalletTransactionEntity.setFromWalletId(fromWalletId);
        virtualWalletTransactionEntity.setToWalletId(toWalletId);
        virtualWalletTransactionEntity.setStatus(0);
        Long transactionId = transactionRepository.saveTransaction(virtualWalletTransactionEntity);
        try {
            debit(fromWalletId,amount);
            credit(toWalletId,amount);
            // 根据具体情况更新status
        }  catch (InstantiationError e) {
            transactionRepository.updateStatus(transactionId, 1);
        } catch (Exception e) {
            transactionRepository.updateStatus(transactionId, 2);
        }
        transactionRepository.updateStatus(transactionId, 3);
    }


    private AnemiaVirtualWalletBo convert() {
        return new AnemiaVirtualWalletBo();
    }
}
