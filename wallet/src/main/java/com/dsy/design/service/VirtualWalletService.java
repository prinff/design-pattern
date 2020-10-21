package com.dsy.design.service;

import com.dsy.design.bo.AnemiaVirtualWalletBo;
import com.dsy.design.domain.VirtualWallet;
import com.dsy.design.entity.VirtualWalletEntity;
import com.dsy.design.repository.VirtualWalletRepository;
import com.dsy.design.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 * @author dsy
 * @date 2020/10/22 04:53
 */
public class VirtualWalletService {
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    private VirtualWallet convert(VirtualWalletEntity virtualWallet) {
        return new VirtualWallet(virtualWallet.getFromWalletId());
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepo.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        // 基本与贫血模型相同
    }

}
